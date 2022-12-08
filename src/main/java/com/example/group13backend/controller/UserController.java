package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.Users;
import com.example.group13backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@ApiMapping("users")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) { this.userServices = userServices; }

    @GetMapping
    public List<Users> getUsers() { return userServices.getAllUsers(); }

    @GetMapping(path = "{userId}")
    public Optional<Users> getUser(@PathVariable("userId") Long userId) {
        return userServices.getUser(userId);
    }
}
