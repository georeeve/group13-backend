package com.example.group13backend.services;

import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.UserRepository;
import com.example.group13backend.utils.Argon2Util;
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
    @Autowired
    public UserServices(UserRepository userRepository, Argon2Util argon2Util, SnowflakeUtil snowflakeUtil) {
        this.userRepository = userRepository;
        this.argon2Util = argon2Util;
        this.snowflakeUtil = snowflakeUtil;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId) {
        boolean userExists = userRepository.existsById(userId);
        if(!userExists) {
            throw new IllegalStateException("User with id " + userId + "does not exist");
        }
        return userRepository.findById(userId);
    }


    public void addNewUser(User user) {
        Optional<User> usersOptional = userRepository.findUsersByEmail(user.getEmail());
        if(usersOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        user.setPassword(argon2Util.hash(user.getPassword()));
        user.setId(snowflakeUtil.newId());

        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + id + " does not exist"
                ));
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + id + " does not exist"
                ));

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
            User emailExist = userRepository.findUsersByEmail(email)
                    .orElseThrow(() -> new IllegalStateException("email taken"));
            user.setEmail(email);
        }

        if (password != null &&
                password.length() > 6 //throw error if too short
        ) {
            user.setPassword(argon2Util.hash(password));
        }
    }
}
