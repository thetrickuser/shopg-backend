package com.shopg.userservice.model;

import com.shopg.userservice.entity.Address;
import com.shopg.userservice.util.ValidateRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    @NotBlank
    private long id;

    @NotBlank
    private String fname;

    private String lname;

    @Email
    @NotBlank
    private String email;

    @ValidateRole
    private String role;

    @Size(min = 10, max = 10)
    private String phone;

    private Address billingAddress;

    private Address shippingAddress;
}
