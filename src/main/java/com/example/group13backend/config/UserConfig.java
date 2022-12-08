package com.example.group13backend.config;

import com.example.group13backend.db.models.Users;
import com.example.group13backend.db.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            Users jon = new Users(
                    "Jon",
                    "James",
                    "jon.james@gmail.com",
                    "test123",
                    LocalDate.of(1992, JANUARY, 15)

            );
            Users mary = new Users(
                    "Mary",
                    "Smith",
                    "test123",
                    "mary.smith@gmail.com",
                    LocalDate.of(1985, JULY, 21)

            );
            Users bill = new Users(
                    "Bill",
                    "Nelson",
                    "test123",
                    "bill.Nelson@gmail.com",
                    LocalDate.of(1999, DECEMBER, 30)

            );
            repository.saveAll(
                    List.of(jon, mary, bill)
            );
        };
    }

}
