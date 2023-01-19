package com.example.authorizationserver.app.dto.response;

import com.example.authorizationserver.app.dto.custom.CustomOAuth2User;
import com.example.authorizationserver.common.Security;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String tokenType = Security.BEARER;
    private CustomOAuth2User user;
    private String provider;

    public LoginResponse(String token, CustomOAuth2User user) {
        this.token = token;
        this.user = user;
    }

    public LoginResponse(String token, CustomOAuth2User user, String provider) {
        this.token = token;
        this.user = user;
        this.provider = provider;
    }
}
