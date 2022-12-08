package com.example.group13backend.services;

import com.example.group13backend.db.models.Users;
import com.example.group13backend.db.repository.UserRepository;
import com.example.group13backend.utils.Argon2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServices {
    private final UserRepository userRepository;
    private final Argon2Util argon2Util;
    @Autowired
    public UserServices(UserRepository userRepository, Argon2Util argon2Util) {
        this.userRepository = userRepository;
        this.argon2Util = argon2Util;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUser(Long userId) {
        boolean userExists = userRepository.existsById(userId);
        if(!userExists) {
            throw new IllegalStateException("User with id " + userId + "does not exist");
        }
        return userRepository.findById(userId);
    }


    public void addNewUser(Users users) {
        Optional<Users> usersOptional = userRepository.findUsersByEmail(users.getEmail());
        if(usersOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        users.setPassword(argon2Util.hash(users.getPassword()));

        userRepository.save(users);
    }
}
