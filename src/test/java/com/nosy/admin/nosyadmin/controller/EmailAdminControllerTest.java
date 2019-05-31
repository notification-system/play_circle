package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.model.EmailProviderProperties;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.service.EmailTemplateService;
import com.nosy.admin.nosyadmin.service.EmailTemplateServiceTest;
import com.nosy.admin.nosyadmin.service.InputSystemService;
import com.nosy.admin.nosyadmin.utils.InputSystemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailAdminControllerTest {

    @InjectMocks
    EmailAdminController emailAdminController;

    @Mock
    EmailTemplateService emailTemplateService;

    @Mock
    InputSystemService inputSystemService;
    @Test
    public void emailTemplatePost() {
        EmailTemplate emailTemplate=mock(EmailTemplate.class);
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.OK, emailAdminController.
                emailTemplatePost("dasda", "dasdas", null,
                        principal).getStatusCode());
    }

    @Test
    public void newType() {
        InputSystemDto inputSystemDto=new InputSystemDto();
        inputSystemDto.setInputSystemName("inputSystemDtoName");
        inputSystemDto.setInputSystemId("inpiutSystemDtoId");
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.CREATED, emailAdminController.newType(inputSystemDto,principal).getStatusCode());

    }

    @Test
    public void getInputSystems() {
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.OK, emailAdminController.getInputSystems(principal).getStatusCode());

    }

    @Test
    public void getEmailAllProviders() {
        Principal principal=mock(Principal.class);
        when(emailTemplateService.getAllEmailProviders()).thenReturn(null);
        assertEquals(HttpStatus.OK, emailAdminController.getEmailAllProviders(principal).getStatusCode());
    }

    @Test
    public void updateInputSystemName() {
    }

    @Test
    public void newEmailTemplate() {
    }

    @Test
    public void getEmailTemplateByInputSystemAndEmailTemplateId() {
    }

    @Test
    public void updateEmailTemplate() {
    }

    @Test
    public void getEmailTemplates() {
    }

    @Test
    public void deleteEmailTemplate() {
    }

    @Test
    public void deleteInputSystem() {
    }
}
