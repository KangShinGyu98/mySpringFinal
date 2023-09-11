package com.fivestar.loginwithjwtoauthform.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERNAME_DUPLICATED(HttpStatus.CONFLICT,"중복된 이메일 입니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 이메일 입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"허가받지 않은 접근입니다.");
    private HttpStatus httpStatus;
    private String message;
}
