package com.example.group13backend.services;

import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.UserRepository;
import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final Logger logger;

    @Autowired
    public AdminUserService(UserRepository userRepository, UserService userService, Logger logger) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.logger = logger;
    }

    public List<User> getAllItems(String authorization) {
        final var user = userService.getCurrentUser(authorization);
        if (user.isAdmin()) {
            return userRepository.findAll();
        }
        logger.error(ErrorMessage.NOT_ADMIN);
        return null;
    }
}
