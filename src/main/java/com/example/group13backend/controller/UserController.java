package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@ApiMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Map<String, String> newUser(@RequestBody User user) {
        return Collections.singletonMap("token", userService.createUser(user));
    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String authorization) {
        final var user = userService.getCurrentUser(authorization);
        userService.deleteUserById(user.getId());
    }

    @PatchMapping
    public void patchUser(
            @RequestHeader("Authorization") String authorization,
            @RequestBody User newUser) {
        final var user = userService.getCurrentUser(authorization);
        userService.patchUserById(user.getId(), newUser);
    }
}
