package com.shopg.userservice.exception;

import com.shopg.userservice.model.Error;
import com.shopg.userservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAccessDenied(BadCredentialsException e) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED.name(), List.of(new Error("password",e.getMessage())));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUserNotFound(UsernameNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND.name(), List.of(new Error("email",e.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        List<Error> errors = e.getFieldErrors().stream()
                .map(fieldError -> new Error(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST.name(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException e) {
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.FORBIDDEN.name(), List.of(new Error("",e.getMessage())));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(String errorCode, List<Error> errors) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setErrors(errors);
        return errorResponse;
    }
}
