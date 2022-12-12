package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@ApiMapping("user")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    public Map<String, String> newUser(@RequestBody User user) {
        return Collections.singletonMap("token", userServices.newUser(user));
    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String authorization) {
        final var user = userServices.getCurrentUser(authorization);
        userServices.deleteUserById(user.getId());
    }

    @PatchMapping
    public void patchUser(
            @RequestHeader("Authorization") String authorization,
            @RequestBody User newUser) {
        final var user = userServices.getCurrentUser(authorization);
        userServices.patchUserById(user.getId(), newUser);
    }
}
