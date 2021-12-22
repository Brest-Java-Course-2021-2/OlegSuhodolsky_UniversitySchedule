package com.epam.brest.service;

import com.epam.brest.daoAPI.DaoUserAPI;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class UserServiceImplMockTest {

    @Mock
    private UserService userService;

    @Mock
    private DaoUserAPI daoUser;

    @AfterEach
    public void check() {
        Mockito.verifyNoMoreInteractions(daoUser);
    }

    @Test
    public void findAll() {

        User user = new User();
        List<User> list = Collections.singletonList(user);
        Mockito.when(userService.getAllUsersService()).thenReturn(list);
        List<User> result = userService.getAllUsersService();
        Mockito.verify(userService).getAllUsersService();
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertSame(result.get(0), user);
    }

    @Test
    public void findUserByID() {
        User user = new User();
        int id = 1;
        Mockito.when(userService.getUserByIdService(id)).thenReturn(user);
        User result = userService.getUserByIdService(id);
        Mockito.verify(userService).getUserByIdService(id);
        Assertions.assertNotNull(result);
        Assertions.assertSame(result, user);
    }

    @Test
    public void createUser() {
        User user = new User();
        int id = 1;
        Mockito.when(userService.createUserService(user)).thenReturn(id);
        Integer result = userService.createUserService(user);
        Mockito.verify(userService).createUserService(user);
        Assertions.assertNotNull(result);
        Assertions.assertSame(result, id);
    }

    @Test
    public void updateUser() {
        User user = new User();
        int id = 1;
        Mockito.when(userService.updateUserService(user)).thenReturn(id);
        Integer result = userService.updateUserService(user);
        Mockito.verify(userService).updateUserService(user);
        Assertions.assertNotNull(result);
        Assertions.assertSame(result, id);
    }

    @Test
    public void deleteUser() {
        Integer id = 0;
        Mockito.when(userService.deleteUserService(id)).thenReturn(0);
        Integer result = userService.deleteUserService(id);
        Mockito.verify(userService).deleteUserService(id);
        Assertions.assertNotNull(result);
        Assertions.assertSame(result, id);
    }
}
