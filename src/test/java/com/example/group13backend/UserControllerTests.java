package com.example.group13backend;

import com.example.group13backend.controller.UserController;
import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDate;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests {
    @LocalServerPort
    private int port;

    private final UserController userController;
    private final UserRepository userRepository;
    private final TestRestTemplate restTemplate;
    private final TestUtils testUtils;

    @Autowired
    public UserControllerTests(UserController userController, UserRepository userRepository, TestRestTemplate restTemplate, TestUtils testUtils) {
        this.userController = userController;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.testUtils = testUtils;
    }

    @BeforeEach
    public void clearRepo() {
        userRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void getCurrentUser() throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        final var token = createExampleUser();
        final var response = getUserWithToken(token);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        final var user = objectMapper.readValue(response.getBody(), User.class);
        assertThat(user.getId()).isNotNull();
        assertThat(user.isAdmin()).isFalse();
        assertThat(user.getFirstName()).isNotNull();
        assertThat(user.getLastName()).isNotNull();
        assertThat(user.getEmail()).isNotNull();
        assertThat(user.getPassword()).isNull();
    }

    @Test
    public void newUsers() {
        assertThat(postNewUser(new User("Test", "Test", "test@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("token");
        assertThat(postNewUser(new User("Test", "Test", "test2@example.com", null, LocalDate.of(2022, JANUARY, 1)))).contains("Null value provided for required field");
        assertThat(postNewUser(new User("", "Test", "test3@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("Not a valid first name or last name");
        assertThat(postNewUser(new User("Test", "", "test4@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("Not a valid first name or last name");
        assertThat(postNewUser(new User("Test", "Test", "example", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("Not a valid email address");
        assertThat(postNewUser(new User("Test", "Test", "test5@example.com", "test123", LocalDate.of(2022, JANUARY, 1)))).contains("Password must be greater than 8 characters long");
        assertThat(postNewUser(new User("Test", "Test", "test@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)))).contains("User is already registered");
    }

    @Test
    public void deleteUser() {
        final var token = createExampleUser();
        assertThat(getUserWithToken(token).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(deleteUserWithToken(token).getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getUserWithToken(token).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(deleteUserWithToken(token).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> getUserWithToken(String token) {
        final var entity = testUtils.getAuthorizationEntity(token);
        return this.restTemplate.exchange(testUtils.getEndpoint("/user", port), HttpMethod.GET, entity, String.class);
    }

    public ResponseEntity<String> deleteUserWithToken(String token) {
        final var entity = testUtils.getAuthorizationEntity(token);
        return this.restTemplate.exchange(testUtils.getEndpoint("/user", port), HttpMethod.DELETE, entity, String.class);
    }

    public String postNewUser(User user) {
        final var entity = new HttpEntity<>(user);
        final var response = this.restTemplate.exchange(testUtils.getEndpoint("/user", port), HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    public String createExampleUser() {
        final var objectMapper = new ObjectMapper();
        final var response = postNewUser(new User("Test", "Test", "test@example.com", "testing123", LocalDate.of(2022, JANUARY, 1)));
        try {
            return objectMapper.readTree(response).get("token").textValue();
        } catch (JsonProcessingException exception) {
            return null;
        }
    }
}
