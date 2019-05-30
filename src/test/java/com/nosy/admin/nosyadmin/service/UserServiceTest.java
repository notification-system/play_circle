package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.config.security.KeycloakClient;
import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.java2d.loops.DrawGlyphListAA;

import javax.servlet.http.HttpServletRequest;

import java.security.GeneralSecurityException;
import java.security.Principal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private KeycloakClient keycloakClient;

    private User user;
    String email="test@nosy.tech";
    @Before
    public void setUser(){

        user=new User();
        user.setEmail(email);
        user.setFirstName("Test");
        user.setLastName("Nosy");
        user.setInfo("TestNosy");
        user.setPassword("dajsndjasn");
    }
    @Test
    public void getUserInfo() {

        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        Principal principal=mock(Principal.class);
        when(httpServletRequest.getUserPrincipal()).thenReturn(principal);
        when(principal.getName()).thenReturn(email);
        when(keycloakClient.getUserInfo(email)).thenReturn(user);
        assertEquals(email,userService.getUserInfo(httpServletRequest).getEmail());
    }

    @Test(expected = Test.None.class)
    public void deleteUser() {
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        Principal principal=mock(Principal.class);
        when(httpServletRequest.getUserPrincipal()).thenReturn(principal);
        when(principal.getName()).thenReturn(email);
        userService.deleteUser(httpServletRequest);

    }

    @Test
    public void logoutUser() {
        HttpServletRequest httpServletRequest=mock(HttpServletRequest.class);
        Principal principal=mock(Principal.class);
        when(httpServletRequest.getUserPrincipal()).thenReturn(principal);
        when(principal.getName()).thenReturn(email);
        userService.logoutUser(httpServletRequest);
    }

    @Test
    public void addUser() {
        when(keycloakClient.registerNewUser(user)).thenReturn(true);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        assertEquals(user.getEmail(),userService.addUser(user).getEmail());
    }

    @Test(expected = GeneralException.class)
    public void addUserWithFalse() {
        when(keycloakClient.registerNewUser(user)).thenReturn(false);
        assertEquals(user.getEmail(),userService.addUser(user).getEmail());
    }

    @Test(expected = GeneralException.class)
    public void addUserWithInvalidPassword() {
        User userWithInvalidPassword=new User();
        user.setEmail("test@nosy.tech");
        user.setPassword("");
        when(keycloakClient.registerNewUser(userWithInvalidPassword)).thenReturn(true);
        assertEquals(user.getEmail(),userService.addUser(userWithInvalidPassword).getEmail());
    }
    @Test(expected = GeneralException.class)
    public void addUserWithInvalidNullPassword() {
        User userWithInvalidPassword=new User();
        user.setEmail("test@nosy.tech");
        when(keycloakClient.registerNewUser(userWithInvalidPassword)).thenReturn(true);
        assertEquals(user.getEmail(),userService.addUser(userWithInvalidPassword).getEmail());
    }
}
