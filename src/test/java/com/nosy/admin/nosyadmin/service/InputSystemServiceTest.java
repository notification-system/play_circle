package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.repository.InputSystemRepository;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InputSystemServiceTest {
    @InjectMocks
    private InputSystemService inputSystemServiceMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private InputSystemRepository inputSystemRepository;


    private String email;
    private InputSystem inputSystem;
    private Set<InputSystem> inputSystemList=new HashSet<>();
    private User user;

    @Before
    public void setUp(){
        email="test@nosy.tech";
        user=new User();
        user.setEmail(email);
        user.setFirstName("Test");
        user.setLastName("Nosy");
        user.setInfo("TestNosy");
        user.setPassword("dajsndjasn");
        inputSystem=new InputSystem();
        inputSystem.setInputSystemId("inputSystemId");
        inputSystem.setInputSystemName("inputSystemName");
        inputSystem.setUser(user);
        inputSystemList.add(inputSystem);
        user.setInputSystem(inputSystemList);
    }

    @Test
    public void getUser(){
        assertEquals(email, inputSystem.getUser().getEmail());
    }

    @Test
    public void getListOfInputSystems() {


        when(userRepositoryMock.findById(email)).thenReturn(Optional.of(user));
        assertEquals(inputSystemList, inputSystemServiceMock.getListOfInputSystems(email));

    }

    @Test(expected = GeneralException.class)
    public void getListOfInputSystemsNotAuthneticated() {
        String email="test@nosy.tech";
        user=new User();
        user.setEmail(email);
        user.setFirstName("Test");
        user.setLastName("Nosy");
        user.setInfo("TestNosy");
        user.setPassword("dajsndjasn");
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemId("inputSystemId");
        inputSystem.setInputSystemName("inputSystemName");
        Set<InputSystem> inputSystemList=new HashSet<>();
        inputSystemList.add(inputSystem);
        user.setInputSystem(inputSystemList);

        when(userRepositoryMock.findById(email)).thenReturn(Optional.empty());
        assertEquals(inputSystemList, inputSystemServiceMock.getListOfInputSystems(email));

    }
    @Test(expected = Test.None.class)
    public void deleteInputSystem() {

        String email="test@nosy.tech";
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemId("inputSystemId");
        inputSystem.setInputSystemName("inputSystemName");
        when(inputSystemRepository.findByIdAndEmail(email,inputSystem.getInputSystemId())).
                thenReturn(inputSystem);
        inputSystemServiceMock.deleteInputSystem(inputSystem.getInputSystemId(), email);




    }

    @Test(expected = Test.None.class)
    public void deleteInputSystemEmailTemplateIsEmptyIsNotNull() {
        String email="test@nosy.tech";
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemId("inputSystemId");
        inputSystem.setInputSystemName("inputSystemName");
        Set<EmailTemplate> emailTemplates=new HashSet<>();

        inputSystem.setEmailTemplate(emailTemplates);
        when(inputSystemRepository.findByIdAndEmail(email,inputSystem.getInputSystemId())).
                thenReturn(inputSystem);
        inputSystemServiceMock.deleteInputSystem(inputSystem.getInputSystemId(), email);

    }

    @Test(expected = GeneralException.class)
    public void deleteInputSystemNotFound() {
        String email="test@nosy.tech";
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemId("inputSystemId");
        inputSystem.setInputSystemName("inputSystemName");
        when(inputSystemRepository.findByIdAndEmail(email,inputSystem.getInputSystemId())).
                thenReturn(null);
        inputSystemServiceMock.deleteInputSystem(inputSystem.getInputSystemId(), email);

    }

    @Test(expected = GeneralException.class)
    public void deleteInputSystemEmailTemplateIsNotEmptyIsNotNull() {
        String email="test@nosy.tech";
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemId("inputSystemId");
        inputSystem.setInputSystemName("inputSystemName");
        Set<EmailTemplate> emailTemplates=new HashSet<>();
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplates.add(emailTemplate);
        inputSystem.setEmailTemplate(emailTemplates);
        when(inputSystemRepository.findByIdAndEmail(email,inputSystem.getInputSystemId())).
                thenReturn(inputSystem);
        inputSystemServiceMock.deleteInputSystem(inputSystem.getInputSystemId(), email);

    }


    @Test(expected = GeneralException.class)
    public void saveInputSystemWithNullEmailTemplate() {
        InputSystem inputSystem=new InputSystem();
        String email="test@nosy.tech";
        inputSystemServiceMock.saveInputSystem(inputSystem, email);
    }

    @Test(expected = GeneralException.class)
    public void saveInputSystemEmptyEmailTemplate() {
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemName(" ");
        String email="test@nosy.tech";
        inputSystemServiceMock.saveInputSystem(inputSystem, email);
    }

    @Test(expected = GeneralException.class)
    public void saveInputSystemWithValidInputSystemNameAlreadyExists() {
        String email="test@nosy.tech";
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemName("inputSystemName");
        when(inputSystemRepository.findByInputSystemNameAndEmail(email,inputSystem.getInputSystemName())).
                thenReturn(inputSystem);
        inputSystemServiceMock.saveInputSystem(inputSystem, email);
    }


    @Test(expected = GeneralException.class)
    public void saveInputSystemWithValidInputSystemNameButUserDoesnotExist() {
        String email="test@nosy.tech";
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemName("inputSystemName");

        when(inputSystemRepository.findByInputSystemNameAndEmail(email,inputSystem.getInputSystemName())).
                thenReturn(null);
        when(userRepositoryMock.findById(email)).thenReturn(Optional.empty());
        inputSystemServiceMock.saveInputSystem(inputSystem, email);
    }

    @Test
    public void saveInputSystemWithValidInputSystemNameButUserExists() {
        String email="test@nosy.tech";
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemName("inputSystemName");
        when(inputSystemRepository.findByInputSystemNameAndEmail(email,inputSystem.getInputSystemName())).
                thenReturn(null);
        when(userRepositoryMock.findById(email)).thenReturn(Optional.of(user));
        when(inputSystemRepository.save(inputSystem)).thenReturn(inputSystem);
        assertEquals(inputSystem.getInputSystemName(),inputSystemServiceMock.saveInputSystem(inputSystem, email).getInputSystemName());
    }

    @Test(expected = GeneralException.class)
    public void updateInputSystemStatusInputSystemDoesntExist() {
        when(inputSystemRepository.findByIdAndEmail(email, inputSystem.getInputSystemId())).thenReturn(null);
        inputSystemServiceMock.updateInputSystemStatus(inputSystem.getInputSystemId(), inputSystem, email);
    }
    @Test(expected = GeneralException.class)
    public void updateInputSystemStatusDuplicateExists() {
        when(inputSystemRepository.findByIdAndEmail(email, inputSystem.getInputSystemId())).thenReturn(inputSystem);
        when(inputSystemRepository.findByInputSystemNameAndEmail(email, inputSystem.getInputSystemName())).thenReturn(inputSystem);
        inputSystemServiceMock.updateInputSystemStatus(inputSystem.getInputSystemId(), inputSystem, email);
    }

    @Test
    public void updateInputSystemStatusDuplicateDoesntExist() {
        when(inputSystemRepository.findByIdAndEmail(email, inputSystem.getInputSystemId())).thenReturn(inputSystem);
        when(inputSystemRepository.findByInputSystemNameAndEmail(email, inputSystem.getInputSystemName())).thenReturn(null);
        when(inputSystemRepository.save(inputSystem)).thenReturn(inputSystem);
        assertEquals(inputSystem.getInputSystemName(),inputSystemServiceMock.updateInputSystemStatus(inputSystem.getInputSystemId(), inputSystem, email).getInputSystemName());
        ;
    }
}
