package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.EmailTemplateDto;
import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.dto.UserDto;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Conversion {

    @Autowired
    private ModelMapper modelMapper;


    public InputSystemDto convertToInputSystemDto(InputSystem inputSystem) {
        return modelMapper.map(inputSystem, InputSystemDto.class);
    }

    public InputSystem convertToInputSystem(InputSystemDto inputSystemDto) {
        return modelMapper.map(inputSystemDto, InputSystem.class);
    }


    public EmailTemplateDto convertToEmailTemplateDto(EmailTemplate emailTemplate) {
        return modelMapper.map(emailTemplate, EmailTemplateDto.class);

    }

    public EmailTemplate convertToEmailTemplate(EmailTemplateDto emailTemplateDto) {
        return modelMapper.map(emailTemplateDto, EmailTemplate.class);

    }

    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }


}
