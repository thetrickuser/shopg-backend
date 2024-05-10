package com.shopg.userservice.mapper;

import com.shopg.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToUserDto(com.shopg.userservice.entity.User user);
}
