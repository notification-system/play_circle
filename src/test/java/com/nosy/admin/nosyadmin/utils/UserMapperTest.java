package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.UserDto;
import com.nosy.admin.nosyadmin.model.InputSystem;
import com.nosy.admin.nosyadmin.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {
    private UserDto userDto=new UserDto();
    private User user=new User();
    @Before
    public void setUp(){
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemId("dadasdsa");
        Set<InputSystem> inputSystemSet=new HashSet<>();
        user.setEmail("TestUser");
        user.setPassword("TestUserPassword");
        user.setLastName("TestUserLastName");
        user.setFirstName("TestUserFirstName");
        user.setInfo("TestUserInfo");
        user.setInputSystem(inputSystemSet);

        userDto.setEmail("TestUserDto");
        userDto.setPassword("TestUserDtoPassword");
        userDto.setLastName("TestUserDtoLastName");
        userDto.setFirstName("TestUserDtoFirstName");
        userDto.setInfo("TestUserDtoInfo");
    }
    @Test
    public void toUserDto(){
        assertEquals(user.getEmail(), UserMapper.INSTANCE.toUserDto(user).getEmail());
        assertEquals(user.getPassword(), UserMapper.INSTANCE.toUserDto(user).getPassword());
        assertEquals(user.getFirstName(), UserMapper.INSTANCE.toUserDto(user).getFirstName());
        assertEquals(user.getLastName(), UserMapper.INSTANCE.toUserDto(user).getLastName());
        assertEquals(user.getInfo(), UserMapper.INSTANCE.toUserDto(user).getInfo());

    }


    @Test
    public void toUser(){
        assertEquals(userDto.getEmail(), UserMapper.INSTANCE.toUser(userDto).getEmail());
        assertEquals(userDto.getPassword(), UserMapper.INSTANCE.toUser(userDto).getPassword());
        assertEquals(userDto.getFirstName(), UserMapper.INSTANCE.toUser(userDto).getFirstName());
        assertEquals(userDto.getLastName(), UserMapper.INSTANCE.toUser(userDto).getLastName());
        assertEquals(userDto.getInfo(), UserMapper.INSTANCE.toUser(userDto).getInfo());

    }


}



