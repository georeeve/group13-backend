package com.example.group13backend.services;

import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.UserRepository;
import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import com.example.group13backend.utils.Argon2Util;
import com.example.group13backend.utils.JWTUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

  private final UserRepository userRepository;
  private final Logger logger;
  private final Argon2Util argon2Util;
  private final JWTUtil jwtUtil;

  @Autowired
  public SessionService(
      UserRepository userRepository, Logger logger, Argon2Util argon2Util, JWTUtil jwtUtil) {
    this.userRepository = userRepository;
    this.logger = logger;
    this.argon2Util = argon2Util;
    this.jwtUtil = jwtUtil;
  }

  public String createSession(User user) {
    Optional<User> dbUserOptional = userRepository.findUsersByEmail(user.getEmail());
    if (dbUserOptional.isEmpty()) {
      logger.error(ErrorMessage.USERNAME_OR_PASSWORD_INCORRECT);
      return null;
    }

    User dbUser = dbUserOptional.get();
    if (argon2Util.verify(user.getPassword(), dbUser.getPassword())) {
      return jwtUtil.sign(dbUser.getId());
    }
    logger.error(ErrorMessage.USERNAME_OR_PASSWORD_INCORRECT);
    return null;
  }

  public Long getSessionId(String authorization) {
    final var token = jwtUtil.getTokenFromHeader(authorization);
    final var id = jwtUtil.getSessionId(token);
    if (id == null) {
      logger.error(ErrorMessage.TOKEN_INVALID);
      return null;
    }
    return id;
  }
}
