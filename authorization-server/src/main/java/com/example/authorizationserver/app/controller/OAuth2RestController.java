package com.example.authorizationserver.app.controller;

import com.example.authorizationserver.app.dto.request.LoginRequest;
import com.example.authorizationserver.app.dto.response.LoginResponse;
import com.example.authorizationserver.app.service.oauth2.IOAuth2Service;
import com.example.authorizationserver.app.service.user.IAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authenticate")
public class OAuth2RestController {

    @Value("${oauth2.google.authorization.request-url}")
    private String googleOauth2Link;
    @Value("${oauth2.facebook.authorization.request-url}")
    private String facebookOauth2Link;

    private final IOAuth2Service ioAuth2Service;
    private final IAppUserService iAppUserService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<String> authenticationLink() {
        return List.of(googleOauth2Link, facebookOauth2Link);
    }

    @GetMapping("/success")
    public LoginResponse loginSuccess(OAuth2AuthenticationToken authentication) {
        if (authentication == null) {
            throw new BadCredentialsException(" no user found ");
        }
        return ioAuth2Service.handleLoginSuccess(authentication);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public LoginResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        return this.iAppUserService.authenticate(loginRequest);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @GetMapping("/not-authorize")
    public Map<String, String> notAuthorize() {
        Map<String, String> err = new HashMap<>();
        err.put("error", "unauthorized");
        return err;
    }


}
