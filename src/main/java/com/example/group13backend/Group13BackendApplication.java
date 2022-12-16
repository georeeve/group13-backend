package com.example.group13backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Group13BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(Group13BackendApplication.class, args);
  }
}
