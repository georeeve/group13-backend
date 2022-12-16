package com.example.group13backend.controller;

import com.example.group13backend.annotations.ApiMapping;
import com.example.group13backend.db.models.User;
import com.example.group13backend.services.UserService;
import com.example.group13backend.views.PublicView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiMapping("user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  @JsonView(PublicView.class)
  public User getCurrentUser(@RequestHeader("Authorization") String authorization) {
    return userService.getCurrentUser(authorization);
  }

  @PostMapping
  public Map<String, String> newUser(@Valid @RequestBody User user) {
    return Collections.singletonMap("token", userService.createUser(user));
  }

  @DeleteMapping
  public void deleteCurrentUser(@RequestHeader("Authorization") String authorization) {
    final var user = userService.getCurrentUser(authorization);
    userService.deleteUserById(user.getId());
  }

  @PatchMapping
  public void patchCurrentUser(
      @RequestHeader("Authorization") String authorization, @RequestBody User newUser) {
    final var user = userService.getCurrentUser(authorization);
    userService.patchUserById(user.getId(), newUser);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return errors;
  }
}
