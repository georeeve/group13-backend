package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@ApiMapping("login")
public class LogInController {
    private final UserServices userServices;

    @Autowired
    public LogInController(UserServices userServices) { this.userServices = userServices; }

    @PostMapping
    public Map<String, String> loginUser(@RequestBody User user) {
        return Collections.singletonMap("token", userServices.logInUser(user));
    }
}

