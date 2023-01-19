package com.example.authorizationserver.app.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> badCredentailsExceptionHandler(BadCredentialsException ex) {
        Map<String, String> err = new HashMap<>();
        err.put("error", ex.getMessage());
        return err;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> usernameNotFoundExceptionHandler(UsernameNotFoundException ex) {
        Map<String, String> err = new HashMap<>();
        err.put("error", ex.getMessage());
        return err;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> bindExceptionHandler(BindException ex) {
        return ex.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage()));
    }

}
