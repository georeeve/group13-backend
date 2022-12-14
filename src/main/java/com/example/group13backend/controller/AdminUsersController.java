package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.AdminUserService;
import com.example.group13backend.services.UserService;
import com.example.group13backend.views.PublicView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiMapping("admin/users")
public class AdminUsersController {
    private final AdminUserService adminUserService;

    @Autowired
    public AdminUsersController(
            AdminUserService adminUserService
    ) {
        this.adminUserService = adminUserService;
    }

    @GetMapping
    @JsonView(PublicView.class)
    public List<User> getAllUsers(@RequestHeader("Authorization") String authorization) {
        return adminUserService.getAllItems(authorization);
    }

    @PatchMapping
    public void patchUser(
            @RequestHeader("Authorization") String authorization,
            @RequestBody User newUser
    ) {
        adminUserService.patchUser(authorization, newUser);
    }

    @DeleteMapping(path = "{toDeleteId}")
    public ResponseEntity<String> deleteUser(
            @RequestHeader("Authorization") String authorization,
            @PathVariable("toDeleteId") Long toDeleteId
    ) {
        return adminUserService.deleteUser(authorization, toDeleteId);
    }

}
