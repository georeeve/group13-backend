package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@ApiMapping("session")
public class SessionController {

    private final UserServices userServices;

    public SessionController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    public Map<String, String> newSession(@RequestBody User user) {
        return Collections.singletonMap("token", userServices.newSession(user));
    }
}
