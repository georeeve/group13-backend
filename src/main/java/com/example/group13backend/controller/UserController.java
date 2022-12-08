package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ApiMapping("users")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) { this.userServices = userServices; }

    @GetMapping
    public List<User> getAllUsers() { return userServices.getAllUsers(); }

    @GetMapping(path = "{userId}")
    public Optional<User> getUser(@PathVariable("userId") Long userId) {
        return userServices.getUser(userId);
    }

    @PostMapping(path = "/register")
    public void registerNewUser(@RequestBody User user) {
        userServices.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUserById(@PathVariable("userId") Long id) {
        userServices.deleteUserById(id);
    }
}
