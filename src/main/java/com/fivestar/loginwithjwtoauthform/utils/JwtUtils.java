package com.fivestar.loginwithjwtoauthform.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtils {

    public static String getUserName(String token, String secretKey){
        log.info("getname");
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userName",String.class);
    }

    public static boolean isExpired(String token, String secretKey){
        log.info("isexpired");
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
    public static String ceateToken(String userName,String key, Long expTimeMs){
        log.info("create token");
        log.info("key = {}",key);
        log.info("userName = {}",userName);
        Claims claims = Jwts.claims();
        claims.put("userName",userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expTimeMs))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }

}
