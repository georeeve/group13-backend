package com.example.group13backend;

import com.example.group13backend.controller.UserController;
import com.example.group13backend.db.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.LocalDate;

import static java.time.Month.JANUARY;
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

    @Test
    public void newUsers() {
        assertThat(postRegister(new User("Test", "Test", "test@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("token");
        assertThat(postRegister(new User("Test", "Test", "test2@example.com", null, LocalDate.of(2022, JANUARY, 1)))).contains("Null value provided for required field");
        assertThat(postRegister(new User("", "Test", "test3@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("Not a valid first name or last name");
        assertThat(postRegister(new User("Test", "", "test4@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("Not a valid first name or last name");
        assertThat(postRegister(new User("Test", "Test", "example", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("Not a valid email address");
        assertThat(postRegister(new User("Test", "Test", "test5@example.com", "test123", LocalDate.of(2022, JANUARY, 1)))).contains("Password must be greater than 8 characters long");
        assertThat(postRegister(new User("Test", "Test", "test@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("User is already registered");
    }

    public String postRegister(User user) {
        return this.restTemplate.postForObject("http://localhost:" + port + "/api/v1/users/register", user, String.class);
    }
}
