package com.example.authorizationserver.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secretKey;

    //    Tạo ra 1 chuỗi JWT
    public String createToken(String username, String role) {
        log.info(this.getClass().getSimpleName());
        log.info("method - createToken()");

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("username", username);
        claims.put("sub", "HauPV");
        claims.put("iat", new Date(System.currentTimeMillis()));
        long expiredIn2Hours = new Date().getTime() + (2 * 60 * 60 * 1000);
        claims.put("exp", new Date(expiredIn2Hours));

        log.info("kết thúc method - createToken()");
        return Jwts.builder()
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

//    public static void main(String[] args) {
//        BCryptPasswordEncoder ecd = new BCryptPasswordEncoder();
    /* $2a$10$Cg2li1NTsJYhnDkLOfmGSu2ojJWumRqsEAZgz5rsgD4omuJ0td57a */
//        System.out.println(ecd.encode("123456"));
//    }
}
