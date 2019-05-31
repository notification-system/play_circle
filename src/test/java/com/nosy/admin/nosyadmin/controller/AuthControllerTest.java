package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.config.security.ClientToken;
import com.nosy.admin.nosyadmin.config.security.KeycloakClient;
import com.nosy.admin.nosyadmin.dto.UserDto;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.service.UserService;
import com.nosy.admin.nosyadmin.utils.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;
@RunWith(MockitoJUnitRunner.class)

public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Mock
    KeycloakClient keycloakClient;

    UserDto userDto=new UserDto();

    @Before
    public void setUp(){
        userDto.setFirstName("testFirstName");
        userDto.setLastName("testLastName");
        userDto.setPassword("testPassword");
        userDto.setEmail("test@nosy.tech");
        userDto.setInfo("testInfo");
    }

    @Test
    public void logout() {
        HttpServletRequest httpServletRequest=Mockito.mock(HttpServletRequest.class);
        doNothing().when(userService).logoutUser(httpServletRequest);
        assertEquals(HttpStatus.NO_CONTENT, authController.logout(httpServletRequest).getStatusCode());
    }

    @Test
    public void isAuthenticated() throws IOException {
        String token="testToken";
        doReturn(true).when(keycloakClient).isAuthenticated(token);
        assertEquals(HttpStatus.OK, authController.isAuthenticated(token).getStatusCode());


    }

    @Test
    public void getToken() throws IOException {
        assertEquals(HttpStatus.OK, authController.getToken(userDto).getStatusCode());
    }

    @Test
    public void newUser() {
        assertEquals(HttpStatus.CREATED, authController.newUser(userDto).getStatusCode());
    }

    @Test
    public void deleteUsername() {
        HttpServletRequest httpServletRequest=Mockito.mock(HttpServletRequest.class);
        doNothing().when(userService).deleteUser(httpServletRequest);
        assertEquals(HttpStatus.NO_CONTENT, authController.deleteUsername(httpServletRequest).getStatusCode());


    }

    @Test
    public void getUserProfile() {
        User user=UserMapper.INSTANCE.toUser(userDto);

        HttpServletRequest httpServletRequest=Mockito.mock(HttpServletRequest.class);
        doReturn(user).when(userService).getUserInfo(httpServletRequest);
        assertEquals(HttpStatus.OK, authController.getUserProfile(httpServletRequest).getStatusCode());
    }

}
