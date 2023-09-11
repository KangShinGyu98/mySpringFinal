package com.fivestar.loginwithjwtoauthform.controller;

import com.fivestar.loginwithjwtoauthform.dto.GlobalResDto;
import com.fivestar.loginwithjwtoauthform.dto.LoginReqDto;
import com.fivestar.loginwithjwtoauthform.dto.UserSignupReqDto;
import com.fivestar.loginwithjwtoauthform.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    @PostMapping("api/user/signup")
    @ResponseStatus(HttpStatus.OK)
    public GlobalResDto signUp(@Validated @RequestBody UserSignupReqDto dto){

        if(userService.signUp(dto.email(), dto.password())) return new GlobalResDto("SUCCESS");
        else return new GlobalResDto("FAIL");
    }

    @PostMapping("api/user/login")
    @ResponseStatus(HttpStatus.OK)
    public GlobalResDto login(@Validated @RequestBody LoginReqDto dto){
        String token = userService.login(dto.email(),dto.password());
        return new GlobalResDto(token);
    }
    @PostMapping("api/posts")
    @ResponseStatus(HttpStatus.OK)
    public GlobalResDto reviewPosts(Authentication authentication){
        return new GlobalResDto(authentication.getName()+ " success" );

    }

}
