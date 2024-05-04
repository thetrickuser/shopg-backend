package com.shopg.userservice.model;

import com.shopg.userservice.util.ValidateRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @Email
    private String email;

    @NotBlank
    private String password;
}
