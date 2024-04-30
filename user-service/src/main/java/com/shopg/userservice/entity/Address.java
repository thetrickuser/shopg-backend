package com.shopg.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
