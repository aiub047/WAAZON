package com.waazon.backend.dtos;

import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class AuthenticationResponse {
    private final String jwt;
    private final UserDetails userDetails;
    public AuthenticationResponse(String jwt, UserDetails userDetails){
        this.jwt=jwt;
        this.userDetails = userDetails;
    }
}
