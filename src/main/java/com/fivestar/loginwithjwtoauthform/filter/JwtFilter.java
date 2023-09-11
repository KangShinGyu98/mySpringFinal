package com.fivestar.loginwithjwtoauthform.filter;

import com.fivestar.loginwithjwtoauthform.exception.ErrorCode;
import com.fivestar.loginwithjwtoauthform.exception.UserException;
import com.fivestar.loginwithjwtoauthform.service.UserService;
import com.fivestar.loginwithjwtoauthform.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //username 토큰에서 꺼내기
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.warn(authorization);

        //token 이 없을때
        if(authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
//            throw new UserException(ErrorCode.UNAUTHORIZED,"허가받지 않은 접근입니다.");
        }

        //token parsing
        String token = authorization.split(" ")[1];
        log.info("with out Bearer : {}",token);
        log.info("secret key : {}",secretKey);
        //expired check
        if (JwtUtils.isExpired(token,secretKey)) {
            filterChain.doFilter(request,response);
            return;
        }

//        String username = JwtUtils.getUserName(token,secretKey);
//        log.info("username : {}", username);
        String username = "";
        //권한부여
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null, List.of(new SimpleGrantedAuthority("USER")));
        //-TODO DB에 role 추가해서 role based 해보기

        //디테일 추가
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }

}
