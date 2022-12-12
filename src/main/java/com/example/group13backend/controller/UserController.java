package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@ApiMapping("users")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }

    @DeleteMapping
    public void deleteCurrentUser(@RequestHeader("Authorization") String authorization) {
        final var user = userServices.getCurrentUser(authorization);
        userServices.deleteUserById(user.getId());
    }

    @PostMapping(path = "/register")
    public Map<String, String> registerUser(@RequestBody User user) {
        return Collections.singletonMap("token", userServices.addNewUser(user));
    }

    @PostMapping(path = "/login")
    public Map<String, String> loginUser(@RequestBody User user) {
        return Collections.singletonMap("token", userServices.logInUser(user));
    }

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userServices.getUser(userId);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUserById(@PathVariable("userId") Long id) {
        userServices.deleteUserById(id);
    }

    @PutMapping(path = "{userId}")
    public void updateUserById(
            @PathVariable("userId") Long userId,
            @RequestBody User newUser
    ) {
        userServices.updateUserById(userId, newUser);
    }
}
