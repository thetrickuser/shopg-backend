package com.shopg.userservice.model;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    String errorCode;
    List<Error> errors;

}
