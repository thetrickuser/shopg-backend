package com.shopg.userservice.model;

import com.shopg.userservice.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Address {

    @NotBlank
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    @Size(min = 6, max = 6)
    private String pincode;
}
