package com.example.authorizationserver.app.config;

import com.example.authorizationserver.common.JwtUtils;
import com.example.authorizationserver.common.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Autowired
    public void config(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeHttpRequests(httpResquest ->
                        httpResquest
                                .requestMatchers(HttpMethod.GET, "/api/authenticate").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/authenticate/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/authenticate/success").authenticated()
                                .anyRequest().hasAuthority(Security.ROLE_ADMIN)
                )
                .oauth2Login()
                .loginPage("/api/authenticate/not-authorize")
                .defaultSuccessUrl("/api/authenticate/success", true)
                .failureUrl("/api/authenticate/not-authorize");
        return http.build();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
