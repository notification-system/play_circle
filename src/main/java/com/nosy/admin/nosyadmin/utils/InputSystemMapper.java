package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.EmailTemplateDto;
import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InputSystemMapper {

    InputSystemMapper INSTANCE = Mappers.getMapper( InputSystemMapper.class );

    InputSystemDto toInputSystemDto(InputSystem inputSystem);
    InputSystem toInputSystem(InputSystemDto emailTemplateDto);
}
