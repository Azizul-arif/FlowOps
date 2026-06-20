package com.flowOps.flowOps_service.security;

import com.flowOps.flowOps_service.dto.loginDto.LoginRequest;
import com.flowOps.flowOps_service.dto.loginDto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
