package com.shopg.userservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    @NotBlank
    private String refreshToken;

    @NotNull
    private UserDto user;


}
