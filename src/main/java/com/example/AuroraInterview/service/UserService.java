package com.example.AuroraInterview.service;

import com.example.AuroraInterview.model.User;
import com.example.AuroraInterview.req.UserRegisterReq;
import com.example.AuroraInterview.resp.UserResp;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User save(UserRegisterReq req);
    List<UserResp> findAll();
}
