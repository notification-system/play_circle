package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.repository.InputSystemRepository;
import com.nosy.admin.nosyadmin.repository.UserRepository;
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


    User user;



    @Test
    public void getListOfInputSystems() {
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

    /*
    InputSystem checkInputSystem = inputSystemRepository.findByIdAndEmail(email, inputSystemId);
    if (checkInputSystem == null) {
      throw new GeneralException(MessageError.NO_INPUT_SYSTEM_FOUND.getMessageText());
    }
    if (!checkInputSystem.getEmailTemplate().isEmpty()) {
      throw new GeneralException(MessageError.INPUT_SYSTEM_HAS_CHILDREN.getMessageText());
    }
    inputSystemRepository.deleteById(inputSystemId);
     */
        String email="test@nosy.tech";
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemId("inputSystemId");
        inputSystem.setInputSystemName("inputSystemName");
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

    @Test
    public void saveInputSystem() {
    }

    @Test
    public void updateInputSystemStatus() {
    }
}
