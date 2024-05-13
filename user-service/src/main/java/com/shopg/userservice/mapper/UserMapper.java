package com.shopg.userservice.mapper;

import com.shopg.userservice.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToUserDto(com.shopg.userservice.entity.User user);
}
