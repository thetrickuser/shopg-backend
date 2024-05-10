package com.shopg.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SignupResponse {
    public SignupResponse(String message) {
        this.message = message;
    }

    private String message;
}
