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
                    LocalDate.of(1992, JANUARY, 15)

            );
            repository.saveAll(
                    List.of(jon)
            );
        };
    }

}
