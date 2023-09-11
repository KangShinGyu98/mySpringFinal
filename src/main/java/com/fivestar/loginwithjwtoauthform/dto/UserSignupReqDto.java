package com.fivestar.loginwithjwtoauthform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserSignupReqDto(
        @Email(regexp = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$")
        @NotBlank
        @Length(min = 5,max = 320)
        String email,
        @NotBlank
        @Length(min = 4,max = 20)
        String password

) {
}