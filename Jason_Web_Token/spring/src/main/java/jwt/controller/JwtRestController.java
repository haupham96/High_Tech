package jwt.controller;

import jwt.config.JwtFilter;
import jwt.config.JwtUtil;
import jwt.model.Hello;
import jwt.model.JwtRequest;
import jwt.model.JwtResponse;
import jwt.service.IAccountService;
import jwt.service.impl.AccountDetailsImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class JwtRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    IAccountService iAccountService;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    PasswordEncoder encoder;

    // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXUxMjMiLCJpYXQiOjE2NTcwODM0NDgsImV4cCI6MTY1NzA5MjA4OH0.N-7eycXPjPgGEAiPmYxDZpwNsblAonVVrd92IFnnsqg

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwt(request.getUsername());

        AccountDetailsImpl accountDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<String> authorities = accountDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return new ResponseEntity<>(new JwtResponse(accountDetails.getId(), accountDetails.getUsername(), jwt, authorities), HttpStatus.OK);
    }

    @GetMapping(value = "/hello",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> sayHello() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

}
