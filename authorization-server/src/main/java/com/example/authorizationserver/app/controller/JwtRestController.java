package com.example.authorizationserver.app.controller;

import com.example.authorizationserver.app.dto.request.LoginRequest;
import com.example.authorizationserver.app.dto.response.LoginResponse;
import com.example.authorizationserver.app.service.user.IAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authenticate/jwt")
public class JwtRestController {
    private final IAppUserService iAppUserService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        return this.iAppUserService.authenticate(loginRequest);
    }
}
