package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.model.InputSystem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public abstract class InputSystemMapper {

    public static final InputSystemMapper INSTANCE = Mappers.getMapper( InputSystemMapper.class );

            @Mapping(source = "inputSystemId", target = "id")
            @Mapping(source = "inputSystemName", target = "name")

    public abstract InputSystemDto toInputSystemDto(InputSystem inputSystem);
            @Mapping(source = "id", target = "inputSystemId")
            @Mapping(source = "name", target = "inputSystemName")

    public abstract InputSystem toInputSystem(InputSystemDto emailTemplateDto);

    public abstract Set<InputSystemDto> toInputSystemDtoSet(Set<InputSystem> inputSystemSet);

}
