package com.example.group13backend;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.group13backend.controller.AdminUsersController;
import com.example.group13backend.db.models.User;
import com.example.group13backend.testutils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminUsersControllerTests {
  @LocalServerPort private int port;

  private final AdminUsersController adminUsersController;
  private final TestUtil testUtil;
  private final TestRestTemplate restTemplate;

  @Autowired
  public AdminUsersControllerTests(
      AdminUsersController adminUsersController, TestUtil testUtil, TestRestTemplate restTemplate) {
    this.adminUsersController = adminUsersController;
    this.testUtil = testUtil;
    this.restTemplate = restTemplate;
  }

  @Test
  public void contextLoads() {
    assertThat(adminUsersController).isNotNull();
  }

  @Test
  public void getAllUsers() throws JsonProcessingException {
    final var objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());

    final var token = testUtil.createExampleUser(true);
    final var entity = testUtil.getAuthorizationEntity(token);

    final var response =
        restTemplate.exchange(
            testUtil.getEndpoint("/admin/users", port), HttpMethod.GET, entity, String.class);
    final var users = objectMapper.readValue(response.getBody(), User[].class);

    assertThat(users)
        .allMatch(
            user ->
                user.getId() != null
                    && user.getFirstName() != null
                    && user.getLastName() != null
                    && user.getEmail() != null
                    && user.getDob() != null);
  }
}
