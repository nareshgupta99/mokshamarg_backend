package com.example.MokshaMarg.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.MokshaMarg.response.AbstractApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AbstractApiResponse> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(new AbstractApiResponse(false, ex.getMessage(), ""), HttpStatus.OK);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<AbstractApiResponse> handleInvalidCredentials(InvalidCredentialsException ex) {
        return new ResponseEntity<AbstractApiResponse>(new AbstractApiResponse(false, ex.getMessage(), ""), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AbstractApiResponse> handleGeneric(Exception ex) {
        return new ResponseEntity<>(new AbstractApiResponse(false, ex.getMessage(),null), HttpStatus.OK);
    }
}
