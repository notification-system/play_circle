package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.model.InputSystem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class InputSystemMapper {

    public static final InputSystemMapper INSTANCE = Mappers.getMapper( InputSystemMapper.class );

    public abstract InputSystemDto toInputSystemDto(InputSystem inputSystem);
    public abstract InputSystem toInputSystem(InputSystemDto emailTemplateDto);
}
