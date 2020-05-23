package com.example.AuroraInterview.controller;

import com.example.AuroraInterview.model.User;
import com.example.AuroraInterview.req.UserRegisterReq;
import com.example.AuroraInterview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

    @Autowired
    UserService userService;

    @ModelAttribute("user")
    public UserRegisterReq userRegistrationDto() {
        return new UserRegisterReq();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegisterReq req,
                                      BindingResult result) {
        User existing = userService.findByEmail(req.getEmail());
        if (null != existing) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(req);
        return "redirect:/register?success";
    }
}
