package com.example.group13backend.logging;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class Logger {

  public void error(ErrorMessage errorMessage) {
    throw new ResponseStatusException(errorMessage.status(), errorMessage.toString());
  }
}
