package com.shopg.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
    private String errorField;
    private String errorMessage;
}
