package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.EmailTemplateDto;
import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ConversionTest {
    @InjectMocks
    private Conversion conversion;
    private InputSystemDto inputSystemDto=new InputSystemDto();
    private InputSystem inputSystem=new InputSystem();


    @Before
    public void setUp(){
        User user=new User();
        user.setEmail("TestUser");
        user.setPassword("TestPassword");
        inputSystem.setUser(user);
        inputSystem.setInputSystemName("inputSystemName");
        inputSystem.setInputSystemId("inputSystemId");
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.setEmailTemplateName("dadsa");
        Set<EmailTemplate> emailTemplateSet=new HashSet<>();
        emailTemplateSet.add(emailTemplate);
        inputSystem.setEmailTemplate(emailTemplateSet);

        EmailTemplateDto emailTemplateDto=new EmailTemplateDto();
        emailTemplateDto.setEmailTemplateName("emailTemplateDto");
        inputSystemDto.setInputSystemId("inputSystemDtoId");
        inputSystemDto.setInputSystemName("inputSystemDtoName");
    }

    @Test
    public void convertToInputSystemDto() {
  //      assertEquals(inputSystem.getInputSystemName(),conversion.convertToInputSystemDto(inputSystem).getInputSystemName());

    }

    @Test
    public void convertToInputSystem() {
    }

    @Test
    public void convertToEmailTemplateDto() {
    }

    @Test
    public void convertToEmailTemplate() {
    }

    @Test
    public void convertToUserDto() {
    }

    @Test
    public void convertToUser() {
    }
}
