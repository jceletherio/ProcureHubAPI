package com.example.procurehub.auth.controller;

import com.example.procurehub.auth.dto.LoginRequest;
import com.example.procurehub.auth.dto.LoginResponse;
import com.example.procurehub.auth.dto.SignupRequest;
import com.example.procurehub.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/signup")
    public LoginResponse signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }
}