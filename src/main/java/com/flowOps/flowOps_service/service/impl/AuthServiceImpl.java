package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.dto.loginDto.LoginRequest;
import com.flowOps.flowOps_service.dto.loginDto.LoginResponse;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.UserRepository;
import com.flowOps.flowOps_service.security.AuthService;
import com.flowOps.flowOps_service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtService.generateAccessToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
