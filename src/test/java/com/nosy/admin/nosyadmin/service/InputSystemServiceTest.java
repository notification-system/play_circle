package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
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
    @Test
    public void deleteInputSystem() {
    }

    @Test
    public void saveInputSystem() {
    }

    @Test
    public void updateInputSystemStatus() {
    }
}
