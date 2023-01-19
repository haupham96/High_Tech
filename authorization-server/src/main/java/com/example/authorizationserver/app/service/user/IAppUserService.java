package com.example.authorizationserver.app.service.user;

import com.example.authorizationserver.app.dto.request.LoginRequest;
import com.example.authorizationserver.app.dto.response.LoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService {
    LoginResponse authenticate(LoginRequest loginRequest);
}
