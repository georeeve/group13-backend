package com.example.group13backend.logging;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {
  NULL_VALUE(HttpStatus.BAD_REQUEST, "Null value provided for required field"),
  EMAIL_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "User is already registered"),
  DESCRIPTION_TOO_LONG(HttpStatus.BAD_REQUEST, "Description longer than 200 characters"),
  QUANTITY_INVALID(HttpStatus.BAD_REQUEST, "Cannot make quantity less than 0"),
  PRICE_INVALID(HttpStatus.BAD_REQUEST, "Cannot make price less than 0"),
  CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "Category not found"),
  TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "Not a valid token"),
  USERNAME_OR_PASSWORD_INCORRECT(HttpStatus.UNAUTHORIZED, "Username or password is incorrect"),
  NOT_ADMIN(HttpStatus.FORBIDDEN, "Must be an administrator to access this resource"),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User is not found"),
  ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "Item is not found"),
  USER_UNDER_AGE(HttpStatus.FORBIDDEN, "User must be over 18");

  private final HttpStatus statusCode;
  private final String message;

  ErrorMessage(HttpStatus statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public String toString() {
    return this.message;
  }

  public HttpStatus status() {
    return this.statusCode;
  }
}
