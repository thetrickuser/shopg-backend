package com.shopg.userservice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    @NotBlank
    private String token;

    @NotBlank
    private String refreshToken;
}
