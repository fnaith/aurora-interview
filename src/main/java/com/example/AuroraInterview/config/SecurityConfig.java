package com.example.AuroraInterview.config;

import com.example.AuroraInterview.model.Role;
import com.example.AuroraInterview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/register**").permitAll()

                .antMatchers("/user/**").hasRole(Role.MANAGER)

                .antMatchers(HttpMethod.GET, "/menu").hasRole(Role.USER)
                .antMatchers(HttpMethod.POST, "/menu").hasRole(Role.USER)
                .antMatchers(HttpMethod.DELETE, "/menu/**").hasRole(Role.USER)
                .antMatchers(HttpMethod.GET, "/menu/**").hasRole(Role.USER)
                .antMatchers(HttpMethod.PUT, "/menu/**").hasRole(Role.USER)

                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll();
    }
}
