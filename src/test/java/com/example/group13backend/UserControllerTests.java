package com.example.group13backend;

import com.example.group13backend.controller.UserController;
import com.example.group13backend.db.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests {

    @LocalServerPort
    private int port;

    private final UserController userController;
    private final TestRestTemplate restTemplate;

    @Autowired
    public UserControllerTests(UserController userController, TestRestTemplate restTemplate) {
        this.userController = userController;
        this.restTemplate = restTemplate;
    }

    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void getAllUsers() {
        final var response = this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/users", User[].class);
        assertThat(response).allMatch(user -> user.getId() != null && user.getEmail() != null && user.getPassword() != null
            && user.getFirstName() != null && user.getLastName() != null && user.getDob() != null);
    }
}
