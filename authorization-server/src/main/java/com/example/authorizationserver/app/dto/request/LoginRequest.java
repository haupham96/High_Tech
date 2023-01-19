package com.example.authorizationserver.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {
    @NotBlank(message = "cannot be empty")
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "cannot be empty")
    @Length(min = 6, message = "password must have at least 6 characters")
    private String password;
}
