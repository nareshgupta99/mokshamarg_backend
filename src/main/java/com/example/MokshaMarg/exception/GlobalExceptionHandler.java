package com.example.MokshaMarg.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.MokshaMarg.response.AbstractApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AbstractApiResponse> handleUserNotFound(UserNotFoundException ex) {
    	return new ResponseEntity<>(new AbstractApiResponse(false, ex.getMessage(), Collections.emptyMap()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<AbstractApiResponse> handleInvalidCredentials(InvalidCredentialsException ex) {
        return new ResponseEntity<AbstractApiResponse>(new AbstractApiResponse(false, ex.getMessage(), Collections.emptyMap()), HttpStatus.NOT_FOUND);
    }
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AbstractApiResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return new ResponseEntity<AbstractApiResponse>(new AbstractApiResponse(false, ex.getMessage(), Collections.emptyMap()), HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<AbstractApiResponse>(false,"something went wrong",Collections.emptyMap() HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResourceNotFoundExcepton.class)
    public ResponseEntity<AbstractApiResponse> handleResourceNotEntityFoundException(ResourceNotFoundExcepton ex) {
    	return new ResponseEntity<>(new AbstractApiResponse(false, ex.getMessage(), Collections.emptyMap()), HttpStatus.NOT_FOUND);
    }
    
    
}
