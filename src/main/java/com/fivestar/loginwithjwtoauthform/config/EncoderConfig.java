package com.fivestar.loginwithjwtoauthform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Security랑 같은 파일에 하면 순환참조 문제가 발생할 수 있음.
@Configuration
public class EncoderConfig {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
