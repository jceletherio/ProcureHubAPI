package com.example.procurehub.auth.service;

import com.example.procurehub.auth.dto.LoginRequest;
import com.example.procurehub.auth.dto.LoginResponse;
import com.example.procurehub.auth.dto.SignupRequest;
import com.example.procurehub.auth.security.JwtService;
import com.example.procurehub.user.entity.User;
import com.example.procurehub.user.enumtype.UserRole;
import com.example.procurehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return buildResponse(user);
    }

    public LoginResponse signup(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.BUYER)
                .build();

        userRepository.save(user);

        return buildResponse(user);
    }

    private LoginResponse buildResponse(User user) {
        return LoginResponse.builder()
                .token(jwtService.generateToken(user.getEmail()))
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}