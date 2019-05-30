package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.config.security.KeycloakClient;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private KeycloakClient keycloakClient;


    @Test
    public void getUserInfo() {

    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void logoutUser() {
    }

    @Test
    public void addUser() {
    }
}
