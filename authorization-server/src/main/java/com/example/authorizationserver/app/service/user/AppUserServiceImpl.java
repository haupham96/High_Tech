package com.example.authorizationserver.app.service.user;

import com.example.authorizationserver.app.dto.custom.CustomOAuth2User;
import com.example.authorizationserver.app.dto.request.LoginRequest;
import com.example.authorizationserver.app.dto.response.LoginResponse;
import com.example.authorizationserver.app.entity.AppUser;
import com.example.authorizationserver.app.repository.IAppUserRepository;
import com.example.authorizationserver.common.JwtUtils;
import com.example.authorizationserver.common.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements IAppUserService {
    private final IAppUserRepository iAppUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        AppUser appUser = this.iAppUserRepository.findByEmailAndIsEnable(loginRequest.getEmail(), true);
        if (appUser == null) {
            throw new UsernameNotFoundException("Not found user : " + loginRequest.getEmail());
        }
        if (passwordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            String token = jwtUtils.createToken(appUser.getEmail(), appUser.getRole());
            CustomOAuth2User user = new CustomOAuth2User(appUser.getEmail(),
                    new HashMap<>(),
                    List.of(new SimpleGrantedAuthority(appUser.getRole())));
            return new LoginResponse(token, user, Provider.JWT);
        } else {
            throw new BadCredentialsException("Invalid Password .");
        }
    }
}
