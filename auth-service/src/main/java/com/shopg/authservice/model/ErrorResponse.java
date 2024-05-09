package com.shopg.authservice.model;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    String errorCode;
    List<Error> errors;

}
