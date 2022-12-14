package com.example.group13backend;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.group13backend.controller.UserController;
import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.UserRepository;
import com.example.group13backend.testutils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests {
  @LocalServerPort private int port;

  private final UserController userController;
  private final UserRepository userRepository;
  private final TestRestTemplate restTemplate;
  private final TestUtil testUtil;

  @Autowired
  public UserControllerTests(
      UserController userController,
      UserRepository userRepository,
      TestRestTemplate restTemplate,
      TestUtil testUtil) {
    this.userController = userController;
    this.userRepository = userRepository;
    this.restTemplate = restTemplate;
    this.testUtil = testUtil;
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

    final var token = testUtil.createExampleUser(false);
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
    assertThat(
            postNewUser(
                new User(
                    "Test",
                    "Test",
                    "test@example.com",
                    "testing123",
                    LocalDate.of(2022, JANUARY, 1),
                    "23 Jon Street",
                    "",
                    "Leeds",
                    "LS1 1SS")))
        .contains("token");
    // TODO: Password presence validation
    //    assertThat(
    //            postNewUser(
    //                new User(
    //                    "Test",
    //                    "Test",
    //                    "test2@example.com",
    //                    null,
    //                    LocalDate.of(2022, JANUARY, 1),
    //                    "23 Jon Street",
    //                    "",
    //                    "Leeds",
    //                    "LS1 1SS")))
    //        .contains("Null value provided for required field");
    assertThat(
            postNewUser(
                new User(
                    "",
                    "Test",
                    "test3@example.com",
                    "testing123",
                    LocalDate.of(2022, JANUARY, 1),
                    "23 Jon Street",
                    "",
                    "Leeds",
                    "LS1 1SS")))
        .contains("First name must not be blank");
    assertThat(
            postNewUser(
                new User(
                    "Test",
                    "",
                    "test4@example.com",
                    "testing123",
                    LocalDate.of(2022, JANUARY, 1),
                    "23 Jon Street",
                    "",
                    "Leeds",
                    "LS1 1SS")))
        .contains("Last name must not be blank");
    assertThat(
            postNewUser(
                new User(
                    "Test",
                    "Test",
                    "example",
                    "testing123",
                    LocalDate.of(2022, JANUARY, 1),
                    "23 Jon Street",
                    "",
                    "Leeds",
                    "LS1 1SS")))
        .contains("Not a valid email address");
    assertThat(
            postNewUser(
                new User(
                    "Test",
                    "Test",
                    "test5@example.com",
                    "test123",
                    LocalDate.of(2022, JANUARY, 1),
                    "23 Jon Street",
                    "",
                    "Leeds",
                    "LS1 1SS")))
        .contains("Password is invalid");
    assertThat(
            postNewUser(
                new User(
                    "Test",
                    "Test",
                    "test@example.com",
                    "testing123",
                    LocalDate.of(2022, JANUARY, 1),
                    "23 Jon Street",
                    "",
                    "Leeds",
                    "LS1 1SS")))
        .contains("User is already registered");
  }

  @Test
  public void deleteUser() {
    final var token = testUtil.createExampleUser(false);
    assertThat(getUserWithToken(token).getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(deleteUserWithToken(token).getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(getUserWithToken(token).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(deleteUserWithToken(token).getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<String> getUserWithToken(String token) {
    final var entity = testUtil.getAuthorizationEntity(token);
    return restTemplate.exchange(
        testUtil.getEndpoint("/user", port), HttpMethod.GET, entity, String.class);
  }

  public ResponseEntity<String> deleteUserWithToken(String token) {
    final var entity = testUtil.getAuthorizationEntity(token);
    return restTemplate.exchange(
        testUtil.getEndpoint("/user", port), HttpMethod.DELETE, entity, String.class);
  }

  public String postNewUser(User user) {
    final var entity = new HttpEntity<>(user);
    final var response =
        restTemplate.exchange(
            testUtil.getEndpoint("/user", port), HttpMethod.POST, entity, String.class);
    return response.getBody();
  }
}
