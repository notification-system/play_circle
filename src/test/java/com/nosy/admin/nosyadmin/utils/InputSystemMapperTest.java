package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InputSystemMapperTest {
//    InputSystemMapper INSTANCE = Mappers.getMapper( InputSystemMapper.class );
//
//    InputSystemDto toInputSystemDto(InputSystem inputSystem);
//    InputSystem toInputSystem(InputSystemDto emailTemplateDto);

    private InputSystemDto inputSystemDto=new InputSystemDto();
    private InputSystem inputSystem=new InputSystem();
    @Before
    public void setUp(){
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemId("inputSystemId");
        inputSystem.setInputSystemName("inputSystemName");
        User user=new User();
        user.setEmail("testUser");
        inputSystem.setUser(user);
        inputSystem.setInputSystemName("inputSystemName");
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.setEmailTemplateName("dadasd");
        Set<EmailTemplate> emailTemplateSet=new HashSet<>();
        emailTemplateSet.add(emailTemplate);
        inputSystem.setInputSystemId("dadasdsa");
        inputSystem.setEmailTemplate(emailTemplateSet);

        InputSystemDto inputSystemDto=new InputSystemDto();
        inputSystemDto.setInputSystemId("inputSystemDtoId");
        inputSystemDto.setInputSystemName("inputSystemDtoName");
        inputSystemDto.setUser(user);
        inputSystemDto.setEmailTemplate(emailTemplateSet);



    }


    @Test
    public void toInputSystemDto(){
        assertEquals(inputSystem.getInputSystemId(),InputSystemMapper.INSTANCE.toInputSystemDto(inputSystem).getInputSystemId());
        assertEquals(inputSystem.getInputSystemName(),InputSystemMapper.INSTANCE.toInputSystemDto(inputSystem).getInputSystemName());
        assertEquals(inputSystem.getEmailTemplate(),InputSystemMapper.INSTANCE.toInputSystemDto(inputSystem).getEmailTemplate());
        assertEquals(inputSystem.getUser(),InputSystemMapper.INSTANCE.toInputSystemDto(inputSystem).getUser());

    }

    @Test
    public void toInputSystem(){
        assertEquals(inputSystemDto.getInputSystemId(),InputSystemMapper.INSTANCE.toInputSystem(inputSystemDto).getInputSystemId());
        assertEquals(inputSystemDto.getInputSystemName(),InputSystemMapper.INSTANCE.toInputSystem(inputSystemDto).getInputSystemName());
        assertEquals(inputSystemDto.getEmailTemplate(),InputSystemMapper.INSTANCE.toInputSystem(inputSystemDto).getEmailTemplate());
        assertEquals(inputSystemDto.getUser(),InputSystemMapper.INSTANCE.toInputSystem(inputSystemDto).getUser());

    }
}
