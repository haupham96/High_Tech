package com.example.authorizationserver.app.service.oauth2;

import com.example.authorizationserver.app.dto.response.LoginResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface IOAuth2Service {
    LoginResponse handleLoginSuccess(OAuth2AuthenticationToken authentication);
}
