package com.example.group13backend.services;

import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.UserRepository;
import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Transactional
    public void patchUser(
            String authorization,
            User newUser
    ) {
        final var adminUser = userService.getCurrentUser(authorization);

        if (adminUser.isAdmin()) {

            Optional<User> userOptional = userRepository.findById(newUser.getId());
            if (userOptional.isEmpty()) {
                logger.error(ErrorMessage.USER_NOT_FOUND);
                return;
            }

            if (newUser.getFirstName() != null) {
                if (newUser.getFirstName().length() == 0) {
                    logger.error(ErrorMessage.NAME_INVALID);
                    return;
                }
            }
            if (newUser.getLastName() != null) {
                if (newUser.getLastName().length() == 0) {
                    logger.error(ErrorMessage.NAME_INVALID);
                    return;
                }
            }

            if (newUser.getEmail() != null) {
                if (!newUser.getEmail().contains("@")) {
                    logger.error(ErrorMessage.EMAIL_INVALID);
                    return;
                }
            }

            User oldUser = userOptional.get();

            if (newUser.getFirstName() != null &&
                    !Objects.equals(oldUser.getFirstName(), newUser.getFirstName())
            ) {
                oldUser.setFirstName(newUser.getFirstName());
            }

            if (newUser.getLastName() != null &&
                    !Objects.equals(oldUser.getLastName(), newUser.getLastName())
            ) {
                oldUser.setLastName(newUser.getLastName());
            }

            if (newUser.getEmail() != null &&
                    !Objects.equals(oldUser.getEmail(), newUser.getEmail())
            ) {
                if (userRepository.findUsersByEmail(newUser.getEmail()).isPresent()) {
                    logger.error(ErrorMessage.EMAIL_ALREADY_REGISTERED);
                    return;
                }
                oldUser.setEmail(newUser.getEmail());
            }
            return;
        }
        logger.error(ErrorMessage.NOT_ADMIN);
    }
}