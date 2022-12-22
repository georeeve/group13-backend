package com.example.group13backend.services;

import com.example.group13backend.db.models.Session;
import com.example.group13backend.db.models.User;
import com.example.group13backend.db.repository.SessionRepository;
import com.example.group13backend.db.repository.UserRepository;
import com.example.group13backend.logging.ErrorMessage;
import com.example.group13backend.logging.Logger;
import com.example.group13backend.utils.Argon2Util;
import com.example.group13backend.utils.JWTUtil;
import com.example.group13backend.utils.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final SessionRepository sessionRepository;
  private final SessionService sessionService;
  private final Argon2Util argon2Util;
  private final SnowflakeUtil snowflakeUtil;
  private final JWTUtil jwtUtil;
  private final Logger logger;

  @Autowired
  public UserService(
      UserRepository userRepository,
      SessionRepository sessionRepository,
      SessionService sessionService,
      Argon2Util argon2Util,
      SnowflakeUtil snowflakeUtil,
      JWTUtil jwtUtil,
      Logger logger) {
    this.userRepository = userRepository;
    this.sessionRepository = sessionRepository;
    this.sessionService = sessionService;
    this.argon2Util = argon2Util;
    this.snowflakeUtil = snowflakeUtil;
    this.jwtUtil = jwtUtil;
    this.logger = logger;
  }

  public User getCurrentUser(String authorization) {
    final var token = jwtUtil.getTokenFromHeader(authorization);
    final var sessionId = jwtUtil.getSessionId(token);
    final var id = sessionService.getUserIdFromSessionId(sessionId);
    if (id == null) {
      logger.error(ErrorMessage.TOKEN_INVALID);
      return null;
    }
    final var user = userRepository.findById(id);
    if (user.isEmpty()) {
      logger.error(ErrorMessage.USER_NOT_FOUND);
      return null;
    }
    return user.get();
  }

  public String createUser(User user) {
    if (user.getEmail() == null
        || user.getPassword() == null
        || user.getFirstName() == null
        || user.getLastName() == null
        || user.getDob() == null) {
      logger.error(ErrorMessage.NULL_VALUE);
      return null;
    }
    //        if (user.getFirstName().length() == 0 || user.getLastName().length() == 0) {
    //            logger.error(ErrorMessage.NAME_INVALID);
    //            return null;
    //        }
    //        if (!user.getEmail().contains("@")) {
    //            logger.error(ErrorMessage.EMAIL_INVALID);
    //        }
    //        if (user.getPassword().length() <= 8) {
    //            logger.error(ErrorMessage.PASSWORD_TOO_SHORT);
    //            return null;
    //        }
    if (userRepository.findUsersByEmail(user.getEmail()).isPresent()) {
      logger.error(ErrorMessage.EMAIL_ALREADY_REGISTERED);
      return null;
    }
    // TODO: Add dob validation
    user.setPassword(argon2Util.hash(user.getPassword()));
    user.setId(snowflakeUtil.newId());
    userRepository.save(user);

    final var sessionId = snowflakeUtil.newId();
    sessionRepository.save(new Session(sessionId, user.getId()));
    return jwtUtil.sign(sessionId);
  }

  public void deleteUserById(Long id) {
    if (userRepository.findById(id).isEmpty()) {
      logger.error(ErrorMessage.USER_NOT_FOUND);
      return;
    }
    userRepository.deleteById(id);
  }

  @Transactional
  public void patchUserById(Long userId, User newUser) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isEmpty()) {
      logger.error(ErrorMessage.USER_NOT_FOUND);
      return;
    }

    User oldUser = userOptional.get();

    if (newUser.getFirstName() != null
        && !Objects.equals(oldUser.getFirstName(), newUser.getFirstName())) {
      oldUser.setFirstName(newUser.getFirstName());
    }

    if (newUser.getLastName() != null
        && !Objects.equals(oldUser.getLastName(), newUser.getLastName())) {
      oldUser.setLastName(newUser.getLastName());
    }

    if (newUser.getEmail() != null && !Objects.equals(oldUser.getEmail(), newUser.getEmail())) {
      if (userRepository.findUsersByEmail(newUser.getEmail()).isPresent()) {
        logger.error(ErrorMessage.EMAIL_ALREADY_REGISTERED);
        return;
      }
      oldUser.setEmail(newUser.getEmail());
    }

    if (newUser.getPassword() != null
        && !argon2Util.verify(newUser.getPassword(), oldUser.getPassword())) {
      oldUser.setPassword(argon2Util.hash(newUser.getPassword()));
    }

    if (newUser.getDob() != null && !Objects.equals(oldUser.getDob(), newUser.getDob())) {
      oldUser.setDob(newUser.getDob());
    }

    if (newUser.getAddressLine1() != null
        && !Objects.equals(oldUser.getAddressLine1(), newUser.getAddressLine1())) {
      oldUser.setAddressLine1(newUser.getAddressLine1());
    }

    if (newUser.getAddressLine2() != null
        && !Objects.equals(oldUser.getAddressLine2(), newUser.getAddressLine2())) {
      oldUser.setAddressLine2(newUser.getAddressLine2());
    }

    if (newUser.getCity() != null && !Objects.equals(oldUser.getCity(), newUser.getCity())) {
      oldUser.setCity(newUser.getCity());
    }

    if (newUser.getPostCode() != null
        && !Objects.equals(oldUser.getPostCode(), newUser.getPostCode())) {
      oldUser.setPostCode(newUser.getPostCode());
    }
  }
}
