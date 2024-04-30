package com.shopg.userservice.mapper;

import com.shopg.userservice.entity.User;
import com.shopg.userservice.model.SignupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class SignupRequestMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(request.getPassword()))")
    public abstract User mapRequestToUser(SignupRequest request);
}
