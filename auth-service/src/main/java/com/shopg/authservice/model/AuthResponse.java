package com.shopg.authservice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    @NotBlank
    private String token;

    @NotBlank
    private String refreshToken;
}
