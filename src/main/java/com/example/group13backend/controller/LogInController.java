package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ApiMapping("login")
public class LogInController {
    private final UserServices userServices;

    @Autowired
    public LogInController(UserServices userServices) { this.userServices = userServices; }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userServices.logInUser(user);
    }

}

