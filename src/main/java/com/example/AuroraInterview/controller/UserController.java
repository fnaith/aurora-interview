package com.example.AuroraInterview.controller;

import com.example.AuroraInterview.resp.UserResp;
import com.example.AuroraInterview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserResp>> list() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
