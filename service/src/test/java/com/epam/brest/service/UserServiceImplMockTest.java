package com.epam.brest.service;


import com.epam.brest.dao.DaoUser;
import com.epam.brest.model.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.util.Collections;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class UserServiceImplMockTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private DaoUser daoUser;


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
        Assertions.assertSame(user, result.get(0));

    }

}
