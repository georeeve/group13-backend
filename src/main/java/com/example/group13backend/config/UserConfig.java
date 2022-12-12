package com.example.group13backend.config;

import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserServices userServices) {
        return args -> {
            userServices.addNewUser(new User(
                    "Jon",
                    "James",
                    "jon.james@gmail.com",
                    "testing123",
                    LocalDate.of(1992, JANUARY, 15)
            ));
            userServices.addNewUser(new User(
                    "Mary",
                    "Smith",
                    "mary.smith@gmail.com",
                    "testing123",
                    LocalDate.of(1985, JULY, 21)
            ));
            userServices.addNewUser(new User(
                    "Bill",
                    "Nelson",
                    "bill.nelson@gmail.com",
                    "testing123",
                    LocalDate.of(1999, DECEMBER, 30)
            ));
        };
    }
}
