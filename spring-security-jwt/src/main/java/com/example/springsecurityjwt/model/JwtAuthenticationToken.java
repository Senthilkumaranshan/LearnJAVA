package com.example.springsecurityjwt.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    //other classes use this as a model for token

    private String token; //we are going to store
    public JwtAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
