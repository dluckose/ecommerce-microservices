package com.ecommerce.userservice.auth.controller;

import com.ecommerce.userservice.auth.dto.LoginRequestDto;
import com.ecommerce.userservice.auth.dto.UserRegistrationRequestDto;
import com.ecommerce.userservice.auth.jwt.JwtUtil;
import com.ecommerce.userservice.auth.service.UserAuthenticationService;
import com.ecommerce.userservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserAuthenticationService authService;

    public AuthController(AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          UserRepository repo,
                          PasswordEncoder encoder,UserAuthenticationService authService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = repo;
        this.encoder = encoder;
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegistrationRequestDto user) {
        authService.createUser(user,encoder);
        return "User registered";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequestDto request) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(authentication);

        return Map.of("token", token);
    }
}
