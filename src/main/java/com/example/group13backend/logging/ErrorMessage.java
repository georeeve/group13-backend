package com.example.group13backend.logging;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    NULL_VALUE(HttpStatus.BAD_REQUEST, "Null value provided for required field"),
    EMAIL_INVALID(HttpStatus.BAD_REQUEST, "Not a valid email address"),
    NAME_INVALID(HttpStatus.BAD_REQUEST, "Not a valid first name or last name"),
    PASSWORD_TOO_SHORT(HttpStatus.BAD_REQUEST, "Password must be greater than 8 characters long"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User is not found"),
    EMAIL_ALREADY_REGISTERED(HttpStatus.FORBIDDEN, "User is already registered"),
    USERNAME_OR_PASSWORD_INCORRECT(HttpStatus.FORBIDDEN, "Username or password is incorrect"),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "Item is not found"),
    DESCRIPTION_TOO_LONG(HttpStatus.BAD_REQUEST, "Description longer than 200 characters");
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
