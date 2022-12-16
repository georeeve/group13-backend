package com.example.group13backend.testutils;

import static java.time.Month.JANUARY;

import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserService;
import java.time.LocalDate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class TestUtil {
  private final UserService userService;

  public TestUtil(UserService userService) {
    this.userService = userService;
  }

  public String getEndpoint(String path, int port) {
    return "http://localhost:" + port + "/api/v1" + path;
  }

  public HttpEntity<String> getAuthorizationEntity(String token) {
    final var httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", "Bearer " + token);

    return new HttpEntity<>(httpHeaders);
  }

  public String createExampleUser(boolean admin) {
    final var user =
        new User(
            "Test",
            "Test",
            "test@example.com",
            "testing123",
            LocalDate.of(2022, JANUARY, 1),
            "23 Jon Street",
            "",
            "Leeds",
            "LS1 1SS");
    if (admin) {
      user.setAdmin(true);
    }
    return userService.createUser(user);
  }
}
