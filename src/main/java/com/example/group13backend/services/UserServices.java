package com.example.group13backend.services;

import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.UserRepository;
import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import com.example.group13backend.utils.Argon2Util;
import com.example.group13backend.utils.JWTUtil;
import com.example.group13backend.utils.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServices {
    private final UserRepository userRepository;
    private final Argon2Util argon2Util;
    private final SnowflakeUtil snowflakeUtil;
    private final JWTUtil jwtUtil;
    private final Logger logger;

    @Autowired
    public UserServices(UserRepository userRepository, Argon2Util argon2Util, SnowflakeUtil snowflakeUtil, JWTUtil jwtUtil, Logger logger) {
        this.userRepository = userRepository;
        this.argon2Util = argon2Util;
        this.snowflakeUtil = snowflakeUtil;
        this.jwtUtil = jwtUtil;
        this.logger = logger;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            logger.error(ErrorMessage.USER_NOT_FOUND);
            return null;
        }
        return user.get();
    }


    public String addNewUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null || user.getFirstName() == null
                || user.getLastName() == null || user.getDob() == null) {
            logger.error(ErrorMessage.NULL_VALUE);
            return null;
        }
        if (user.getFirstName().length() == 0 || user.getLastName().length() == 0) {
            logger.error(ErrorMessage.NAME_INVALID);
            return null;
        }
        if (!user.getEmail().contains("@")) {
            logger.error(ErrorMessage.EMAIL_INVALID);
        }
        if (user.getPassword().length() <= 8) {
            logger.error(ErrorMessage.PASSWORD_TOO_SHORT);
            return null;
        }
        if (userRepository.findUsersByEmail(user.getEmail()).isPresent()) {
            logger.error(ErrorMessage.EMAIL_ALREADY_REGISTERED);
            return null;
        }
        // TODO: Add dob validation
        user.setPassword(argon2Util.hash(user.getPassword()));
        user.setId(snowflakeUtil.newId());

        userRepository.save(user);
        return jwtUtil.sign(user.getId());
    }

    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            logger.error(ErrorMessage.USER_NOT_FOUND);
            return;
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUserById(
            Long userId,
            User newUser
    ) {
        Optional<User> userOptional = userRepository.findById(userId);
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

        if (newUser.getPassword() != null) {
            if (newUser.getPassword().length() <= 8) {
                logger.error(ErrorMessage.PASSWORD_TOO_SHORT);
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

        if (newUser.getPassword() != null && !argon2Util.verify(newUser.getPassword(), oldUser.getPassword())) {
            oldUser.setPassword(argon2Util.hash(newUser.getPassword()));
        }
    }

    public String logInUser(User user) {
        Optional<User> dbUserOptional = userRepository.findUsersByEmail(user.getEmail());
        if (dbUserOptional.isEmpty()) {
            logger.error(ErrorMessage.USERNAME_OR_PASSWORD_INCORRECT);
            return null;
        }

        User dbUser = dbUserOptional.get();
        if (argon2Util.verify(user.getPassword(), dbUser.getPassword())) {
            return jwtUtil.sign(dbUser.getId());
        }
        logger.error(ErrorMessage.USERNAME_OR_PASSWORD_INCORRECT);
        return null;
    }
}
