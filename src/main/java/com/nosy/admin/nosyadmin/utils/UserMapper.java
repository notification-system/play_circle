package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.UserDto;
import com.nosy.admin.nosyadmin.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
