package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.model.*;
import com.nosy.admin.nosyadmin.repository.EmailTemplateRepository;
import com.nosy.admin.nosyadmin.repository.InputSystemRepository;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

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
    private Producer producer;
    @Mock
    private InputSystemRepository inputSystemRepository;
    @Mock
    ReadyEmail readyEmail;
    private EmailProviderProperties emailProviderProperties;
    private String emailTemplateId;
    private String inputSystemId;
    private String email;
    private EmailTemplate emailTemplate;
    private User user;
    private InputSystem inputSystem;
/*
    private ReadyEmail readyEmail;
*/

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
        emailTemplate.setEmailTemplateId(emailTemplateId);
        emailTemplate.setEmailTemplateTo(emailsTo);
        emailTemplate.setText("Test Message");
        emailTemplate.setSubject("Test Subject");
        emailTemplate.setEmailTemplateName("Test Email Template Name");
        emailTemplate.setRetryPeriod(1);
        emailTemplate.setPriority(1);
        emailTemplate.setFromAddress("testFromAddress@nosy.tech");
        inputSystem=new InputSystem();
        emailTemplate.setInputSystem(inputSystem);
        user=new User();
        user.setEmail(email);
        user.setFirstName("Test");
        user.setLastName("Nosy");
        user.setInfo("TestNosy");
        user.setPassword("dajsndjasn");
        inputSystem.setInputSystemName("testInputSystem");
        inputSystem.setInputSystemId(inputSystemId);
        inputSystem.setUser(user);
        readyEmail=new ReadyEmail();
        readyEmail.setEmailTemplate(emailTemplate);
        emailProviderProperties=new EmailProviderProperties();
        emailProviderProperties.setPassword("TestPassword");
        List<PlaceHolders> placeHolders=new ArrayList<>();
        emailProviderProperties.setPlaceholders(placeHolders);
        emailProviderProperties.setUsername("Test");

        readyEmail.setEmailProviderProperties(emailProviderProperties);




    }

    @Before
    public void beforeEmailTemplate(){

        setVariables();

    }
    @Test(expected= GeneralException.class)
    public void getEmailTemplateByIdGeneralException() {
        when(userRepository.findById(email)).thenReturn(Optional.of(user));
        assertEquals(emailTemplateServiceMock.getEmailTemplateById(inputSystemId, emailTemplateId, email).getEmailTemplateId(),
                emailTemplateId);

    }
    @Test
    public void getEmailTemplateById() {
        doReturn(emailTemplate).when(emailTemplateRepositoryMock).findEmailTemplatesByInputSystemIdAndEmailTemplateId
                (anyString(), anyString());


        when(userRepository.findById(email)).thenReturn(Optional.of(user));
        doReturn(inputSystem).when(inputSystemRepository).findByIdAndEmail(anyString(), anyString());
        assertEquals(emailTemplateId, emailTemplateServiceMock.getEmailTemplateById(inputSystemId, emailTemplateId, email).getEmailTemplateId()
                );

    }

    @Test
    public void newEmailTemplate() {

        when(userRepository.findById(email)).thenReturn(Optional.of(user));
        doReturn(inputSystem).when(inputSystemRepository).findByIdAndEmail(anyString(), anyString());
        doReturn(emailTemplate).when(emailTemplateRepositoryMock).save(any());
        assertEquals(emailTemplateId, emailTemplateServiceMock.newEmailTemplate(emailTemplate, inputSystemId, email).getEmailTemplateId());

    }

    @Test
    public void getAllEmailProviders() {
        ArrayList<String> providers=new ArrayList<>();
        providers.add("DEFAULT");
        providers.add("YANDEX");
        providers.add("GMAIL");

        assertEquals(providers, emailTemplateServiceMock.getAllEmailProviders());
    }

    @Test
    public void deleteEmailTemplate() {
        doReturn(emailTemplate).when(emailTemplateRepositoryMock).findEmailTemplatesByInputSystemIdAndEmailTemplateId
                (anyString(), anyString());


        when(userRepository.findById(email)).thenReturn(Optional.of(user));
        doReturn(inputSystem).when(inputSystemRepository).findByIdAndEmail(anyString(), anyString());
        emailTemplateServiceMock.deleteEmailTemplate(inputSystemId, emailTemplateId, email);
        assertNotNull(inputSystemId);
    }

    @Test
    public void getListOfEmailTemplates() {
        doReturn(inputSystem).when(inputSystemRepository).findByIdAndEmail(anyString(), anyString());
        List<EmailTemplate> listOfEmailTemplates=new ArrayList<>();
        listOfEmailTemplates.add(emailTemplate);

        when(emailTemplateRepositoryMock.findEmailTemplatesByInputSystemId(inputSystemId)).thenReturn(listOfEmailTemplates);
        assertEquals(listOfEmailTemplates,emailTemplateServiceMock.getListOfEmailTemplates(inputSystemId, email));



    }

    @Test
    public void getListEmailTemplatesListNull() {
        doReturn(inputSystem).when(inputSystemRepository).findByIdAndEmail(anyString(), anyString());
        when(emailTemplateRepositoryMock.findEmailTemplatesByInputSystemId(inputSystemId)).thenReturn(null);
        assertNull(emailTemplateServiceMock.getListOfEmailTemplates(inputSystemId, email));



    }
    @Test
    public void getListEmailTemplatesListEmpty() {
        doReturn(inputSystem).when(inputSystemRepository).findByIdAndEmail(anyString(), anyString());
        List<EmailTemplate> listOfEmailTemplates=new ArrayList<>();
        when(emailTemplateRepositoryMock.findEmailTemplatesByInputSystemId(inputSystemId)).thenReturn(listOfEmailTemplates);
        assertEquals(listOfEmailTemplates,emailTemplateServiceMock.getListOfEmailTemplates(inputSystemId, email));
    }

    @Test
    public void postEmailTemplate() {
        doReturn(inputSystem).when(inputSystemRepository).findByIdAndEmail(anyString(), anyString());
        when(userRepository.findById(email)).thenReturn(Optional.of(user));

        doReturn(emailTemplate).when(emailTemplateRepositoryMock).findEmailTemplatesByInputSystemIdAndEmailTemplateId
                (anyString(), anyString());

        assertEquals(emailTemplateId, emailTemplateServiceMock.postEmailTemplate(inputSystemId, emailTemplateId, emailProviderProperties, email).getEmailTemplateId());
    }

    @Test
    public void updateEmailTemplate() {
        doReturn(inputSystem).when(inputSystemRepository).findByIdAndEmail(anyString(), anyString());
        when(userRepository.findById(email)).thenReturn(Optional.of(user));

        doReturn(emailTemplate).when(emailTemplateRepositoryMock).findEmailTemplatesByInputSystemIdAndEmailTemplateId
                (anyString(), anyString());
        assertEquals(emailTemplate,emailTemplateServiceMock.updateEmailTemplate(emailTemplate, inputSystemId, emailTemplateId, email));

    }
}
