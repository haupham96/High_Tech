package com.example.authorizationserver.app.service.oauth2;

import com.example.authorizationserver.app.dto.custom.CustomOAuth2User;
import com.example.authorizationserver.app.dto.response.LoginResponse;
import com.example.authorizationserver.common.Security;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements IOAuth2Service {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final RestTemplate restTemplate;

    @Override
    public LoginResponse handleLoginSuccess(OAuth2AuthenticationToken authentication) {
        LoginResponse loginResponse = new LoginResponse();
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName()
        );
        String urlEndPoint = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
        if (urlEndPoint == null || "".equals(urlEndPoint)) {
            throw new BadCredentialsException("Token Not Found");
        }
        loginResponse.setProvider(authentication.getAuthorizedClientRegistrationId());
        loginResponse.setToken(client.getAccessToken().getTokenValue());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, Security.BEARER + loginResponse.getToken());
        HttpEntity httpEntity = new HttpEntity("", httpHeaders);
        ResponseEntity<Map> response = restTemplate.exchange(urlEndPoint, HttpMethod.GET, httpEntity, Map.class);
        if (HttpStatus.OK == response.getStatusCode()) {
            Map<String, Object> attributes = response.getBody();
            CustomOAuth2User user = new CustomOAuth2User((String) attributes.get("email"),
                    attributes,
                    List.of(new SimpleGrantedAuthority(Security.ROLE_USER)));
            loginResponse.setUser(user);
            return loginResponse;
        } else {
            throw new BadCredentialsException("Token Not Found");
        }
    }
}
