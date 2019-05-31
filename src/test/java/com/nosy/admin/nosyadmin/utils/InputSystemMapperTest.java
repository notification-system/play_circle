package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.dto.UserDto;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

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


        Set<InputSystem> inputSystemSet=new HashSet<>();
        user.setEmail("TestUser");
        user.setPassword("TestUserPassword");
        user.setLastName("TestUserLastName");
        user.setFirstName("TestUserFirstName");
        user.setInfo("TestUserInfo");
        user.setInputSystem(inputSystemSet);


    }


    @Test
    public void toInputSystemDto(){
        assertEquals(inputSystem.getInputSystemId(),InputSystemMapper.INSTANCE.toInputSystemDto(inputSystem).getInputSystemId());
        assertEquals(inputSystem.getInputSystemName(),InputSystemMapper.INSTANCE.toInputSystemDto(inputSystem).getInputSystemName());
        assertEquals(inputSystem.getEmailTemplate(),InputSystemMapper.INSTANCE.toInputSystemDto(inputSystem).getEmailTemplate());
        assertEquals(inputSystem.getUser(),InputSystemMapper.INSTANCE.toInputSystemDto(inputSystem).getUser());

    }
}
