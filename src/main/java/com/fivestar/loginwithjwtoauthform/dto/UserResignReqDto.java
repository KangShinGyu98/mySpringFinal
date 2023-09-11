package com.fivestar.loginwithjwtoauthform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

public record UserResignReqDto (
        @NotEmpty
        String password
){
}
