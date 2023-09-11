package com.fivestar.loginwithjwtoauthform.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class UserException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

}
