package com.waazon.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.waazon.backend.dtos.AuthenticationRequest;
import com.waazon.backend.dtos.AuthenticationResponse;
import com.waazon.backend.dtos.UserRegisterDTO;
import com.waazon.backend.services.UserService;
import com.waazon.backend.services.MyUserDetails.LoginUserDetailsService;
import com.waazon.backend.utils.JwtUtil;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final LoginUserDetailsService loginUserDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, LoginUserDetailsService loginUserDetailsService, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.loginUserDetailsService = loginUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest)
            throws Exception {
        final UserDetails userDetails = loginUserDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Invalid username or password", ex);
        }

        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        boolean isUserCreated = userService.createUser(userRegisterDTO);
        if (isUserCreated) {
            UserDetails userDetails = loginUserDetailsService.loadUserByUsername(userRegisterDTO.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails));
        }
        return ResponseEntity.ok(new AuthenticationResponse(null, null));
    }
}
