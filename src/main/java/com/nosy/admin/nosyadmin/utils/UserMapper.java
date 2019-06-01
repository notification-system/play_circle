package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.UserDto;
import com.nosy.admin.nosyadmin.model.User;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    public abstract UserDto toUserDto(User user);
    public abstract User toUser(UserDto userDto);
}
