package com.example.group13backend.logging;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User is not found"),
    EMAIL_ALREADY_REGISTERED(HttpStatus.FORBIDDEN, "User is already registered"),
    USERNAME_OR_PASSWORD_INCORRECT(HttpStatus.FORBIDDEN, "Username or password is incorrect");

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
