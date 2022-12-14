package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@ApiMapping("session")
public class SessionController {

  private final SessionService sessionService;

  public SessionController(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @PostMapping
  public Map<String, String> newSession(@RequestBody User user) {
    return Collections.singletonMap("token", sessionService.createSession(user));
  }

  @DeleteMapping
  public void deleteSession(@RequestHeader("Authorization") String authorization) {
    final var sessionId = sessionService.getSessionId(authorization);
    sessionService.deleteSession(sessionId);
  }
}
