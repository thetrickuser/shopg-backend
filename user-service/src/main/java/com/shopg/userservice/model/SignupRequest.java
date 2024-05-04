package com.shopg.userservice.model;

import com.shopg.userservice.util.ValidateRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    private String fname;

    private String lname;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @ValidateRole
    private String role;

    @Size(min = 10, max = 10)
    private String phone;

    @Valid
    private Address billingAddress;

    @Valid
    private Address shippingAddress;

}
