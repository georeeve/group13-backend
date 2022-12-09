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
        if (userRepository.findUsersByEmail(user.getEmail()).isPresent()) {
            logger.error(ErrorMessage.EMAIL_ALREADY_REGISTERED);
            return null;
        }
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
            Long id,
            String firstName,
            String lastName,
            String email,
            String password
    ) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            logger.error(ErrorMessage.USER_NOT_FOUND);
            return;
        }

        User user = userOptional.get();
        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(user.getFirstName(), firstName)
        ) {
            user.setFirstName(firstName);
        }

        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(user.getLastName(), lastName)
        ) {
            user.setLastName(lastName);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(user.getEmail(), email)
        ) {
            if (userRepository.findUsersByEmail(email).isPresent()) {
                logger.error(ErrorMessage.EMAIL_ALREADY_REGISTERED);
                return;
            }
            user.setEmail(email);
        }

        if (password != null &&
                password.length() > 6 //throw error if too short
        ) {
            user.setPassword(argon2Util.hash(password));
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
