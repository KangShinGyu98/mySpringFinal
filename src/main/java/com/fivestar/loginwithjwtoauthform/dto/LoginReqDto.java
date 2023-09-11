package com.fivestar.loginwithjwtoauthform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record LoginReqDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {


}
