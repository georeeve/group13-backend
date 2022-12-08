package com.example.group13backend.services;

import com.example.group13backend.db.models.Users;
import com.example.group13backend.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        userRepository.save(users);
    }
}
