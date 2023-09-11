package com.fivestar.loginwithjwtoauthform.service;

import com.fivestar.loginwithjwtoauthform.config.EncoderConfig;
import com.fivestar.loginwithjwtoauthform.domain.Member;
import com.fivestar.loginwithjwtoauthform.dto.LoginReqDto;
import com.fivestar.loginwithjwtoauthform.exception.ErrorCode;
import com.fivestar.loginwithjwtoauthform.exception.UserException;
import com.fivestar.loginwithjwtoauthform.repository.UserRepository;
import com.fivestar.loginwithjwtoauthform.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret}")
    private String key;
    private Long expiredTime = 1000 * 60 * 30L;
    public boolean signUp(String email, String password){
        // 중복확인
        if(userRepository.existsByEmail(email)) throw new UserException(ErrorCode.USERNAME_DUPLICATED,email + " 은(는) 이미 존재하는 회원입니다.");

        //user 객체 생성 및 저장 로직
        Member newUser = new Member(null,email, encoder.encode(password));
        userRepository.save(newUser);
        return true;

    }

    public String login(String email, String password){
        //아이디가 없음
        Member user = userRepository.findByEmail(email).orElseThrow(()->new UserException(ErrorCode.USERNAME_NOT_FOUND,"가입되지 않은 이메일 입니다."));
        //아이디는 있는데 비밀번호가 틀림
        if(!encoder.matches(password, user.getPassword())) throw new UserException(ErrorCode.INVALID_PASSWORD,"비밀번호가 일치하지 않습니다.");
        // 성공 => 토큰 발행
        String token = JwtUtils.ceateToken(user.getEmail(),key,expiredTime);
        log.info("key = {}",key);
        return token;
    }


}
