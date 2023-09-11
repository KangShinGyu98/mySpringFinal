package com.fivestar.loginwithjwtoauthform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManageer {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> userAlreadyExistsExceptionHnadler(UserException e){
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(e.getMessage());
    }
}
