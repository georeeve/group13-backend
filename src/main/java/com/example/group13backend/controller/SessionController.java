package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.DisallowedSession;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.DisallowedSessionService;
import com.example.group13backend.services.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@ApiMapping("session")
public class SessionController {

  private final SessionService sessionService;
  private final DisallowedSessionService disallowedSessionService;

  public SessionController(
      SessionService sessionService, DisallowedSessionService disallowedSessionService) {
    this.sessionService = sessionService;
    this.disallowedSessionService = disallowedSessionService;
  }

  @PostMapping
  public Map<String, String> newSession(@RequestBody User user) {
    return Collections.singletonMap("token", sessionService.createSession(user));
  }

  @DeleteMapping
  public void deleteSession(@RequestHeader("Authorization") String authorization) {
    final var sessionId = sessionService.getSessionId(authorization);
    disallowedSessionService.createDisallowedSession(new DisallowedSession(sessionId));
  }
}
