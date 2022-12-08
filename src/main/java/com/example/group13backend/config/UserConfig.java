package com.example.group13backend.config;

import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.UserRepository;

import com.example.group13backend.utils.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {
    private final SnowflakeUtil snowflakeUtil;
    @Autowired
    public UserConfig(SnowflakeUtil snowflakeUtil) {
        this.snowflakeUtil = snowflakeUtil;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User jon = new User(
                    snowflakeUtil.newId(),
                    "Jon",
                    "James",
                    "jon.james@gmail.com",
                    "test123",
                    LocalDate.of(1992, JANUARY, 15)

            );
            User mary = new User(
                    snowflakeUtil.newId(),
                    "Mary",
                    "Smith",
                    "test123",
                    "mary.smith@gmail.com",
                    LocalDate.of(1985, JULY, 21)

            );
            User bill = new User(
                    snowflakeUtil.newId(),
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
