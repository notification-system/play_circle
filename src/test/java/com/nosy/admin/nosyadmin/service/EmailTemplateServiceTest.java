package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.model.EmailFromProvider;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.repository.EmailTemplateRepository;
import com.nosy.admin.nosyadmin.repository.InputSystemRepository;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class EmailTemplateServiceTest {

    @InjectMocks
    private EmailTemplateService emailTemplateServiceMock;
    @Mock
    private EmailTemplateRepository emailTemplateRepositoryMock;
    @Mock
    private UserRepository userRepository;

    @Mock
    private InputSystemRepository inputSystemRepository;
    private String emailTemplateId;
    private String inputSystemId;
    private String email;
    private EmailTemplate emailTemplate;
    private User user;
    private InputSystem inputSystem;

    private void setVariables(){
        emailTemplateId="emailTemplateId";
        inputSystemId="inputSystemId" ;
        email="test@nosy.tech";
        emailTemplate=new EmailTemplate();
        emailTemplate.setEmailFromProvider(EmailFromProvider.DEFAULT);
        Set<String> emailsCc=new HashSet<>();
        emailsCc.add("testCc@nosy.tech");
        emailTemplate.setEmailTemplateCc(emailsCc);
        Set<String> emailsTo=new HashSet<>();
        emailsTo.add("testTo@nosy.tech");
        emailTemplate.setEmailTemplateTo(emailsTo);
        emailTemplate.setText("Test Message");
        emailTemplate.setSubject("Test Subject");
        emailTemplate.setEmailTemplateName("Test Email Template Name");
        emailTemplate.setRetryPeriod(1);
        emailTemplate.setPriority(1);
        emailTemplate.setFromAddress("testFromAddress@nosy.tech");
        inputSystem=new InputSystem();
        user=new User();
        user.setEmail(email);
        user.setFirstName("Test");
        user.setLastName("Nosy");
        user.setInfo("TestNosy");
        user.setPassword("dajsndjasn");
        inputSystem.setInputSystemName("testInputSystem");
        inputSystem.setInputSystemId(inputSystemId);
        inputSystem.setUser(user);





    }

    @Before
    public void beforeEmailTemplate(){

        setVariables();

    }
    @Test(expected= GeneralException.class)
    public void getEmailTemplateById() {
        doReturn(emailTemplate).when(emailTemplateRepositoryMock).findEmailTemplatesByInputSystemIdAndEmailTemplateId(inputSystemId, emailTemplateId);
//
//        when(emailTemplateRepositoryMock.findEmailTemplatesByInputSystemIdAndEmailTemplateId(inputSystemId, emailTemplateId))
//                .thenReturn(emailTemplate);

        when(userRepository.findById(email)).thenReturn(Optional.of(user));
        doReturn(null).when(inputSystemRepository).findByIdAndEmail(email, inputSystemId);
    assertEquals(emailTemplateServiceMock.getEmailTemplateById(inputSystemId, emailTemplateId, email).getEmailTemplateId(),
                emailTemplateId);

    }


    @Test
    public void newEmailTemplate() {
    }

    @Test
    public void getAllEmailProviders() {
    }

    @Test
    public void deleteEmailTemplate() {
    }

    @Test
    public void getListOfEmailTemplates() {
    }

    @Test
    public void postEmailTemplate() {
    }

    @Test
    public void updateEmailTemplate() {
    }
}
