package com.example.dev.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Invalid Input Value"),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access is Denied"),
    EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "Email is Duplication"),
    LOGIN_INPUT_INVALID(HttpStatus.BAD_REQUEST, "Login input is invalid"),
    FILE_DELETE_ERROR(HttpStatus.BAD_REQUEST, "File delete fail"),



    UNAUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "unauthorized token"),
    EXPIRED_TOKEN(HttpStatus.FORBIDDEN, "expired token"),

    ;

    private final HttpStatus status;
    private final String message;
}
