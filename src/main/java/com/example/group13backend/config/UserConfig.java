package com.example.group13backend.config;

import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
            User jon = new User(
                    "Jon",
                    "James",
                    "jon.james@gmail.com",
                    "testing123",
                    LocalDate.of(1992, JANUARY, 15),
                    "23 Jon Street",
                    "",
                    "Leeds",
                    "LS1 1SS");
            jon.setAdmin(true);
            userService.createUser(jon);
            userService.createUser(new User(
                    "Mary",
                    "Smith",
                    "mary.smith@gmail.com",
                    "testing123",
                    LocalDate.of(1985, JULY, 21),
                    "153 Green Drive",
                    "",
                    "Leeds",
                    "LS3 4WD"));
            userService.createUser(new User(
                    "Bill",
                    "Nelson",
                    "bill.nelson@gmail.com",
                    "testing123",
                    LocalDate.of(1999, DECEMBER, 30),
                    "2 Main Street",
                    "",
                    "Leeds",
                    "LS12 1XX"));
        };

    }
}
