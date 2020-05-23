package com.example.AuroraInterview.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.AuroraInterview.model.Role;
import com.example.AuroraInterview.model.User;
import com.example.AuroraInterview.repository.UserRepository;
import com.example.AuroraInterview.req.UserRegisterReq;
import com.example.AuroraInterview.resp.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean // Singleton
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(UserRegisterReq req) {
        User user = new User();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setEmail(req.getEmail());
        user.setPassword(bCryptPasswordEncoder().encode(req.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(Role.USER));
        if (req.getManager()) {
            roles.add(new Role(Role.MANAGER));
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public List<UserResp> findAll() {
        return userRepository.findAll().stream().map(user -> {
            UserResp resp = new UserResp();
            resp.setFirstName(user.getFirstName());
            resp.setLastName(user.getLastName());
            resp.setEmail(user.getEmail());
            resp.setManager(user.getRoles().stream().anyMatch(role -> Role.MANAGER.equals(role.getName())));
            return resp;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}
