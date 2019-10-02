package com.example.springsecurityjwt.security;

import com.example.springsecurityjwt.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    private String secret="youtube";

    public JwtUser validate(String token) {

        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        JwtUser jwtUser = new JwtUser();
        jwtUser.setUserName(body.getSubject());
        jwtUser.setId(Long.parseLong((String)body.getId()));
        jwtUser.setRole((String) body.get("role"));

        return jwtUser;
    }
}
