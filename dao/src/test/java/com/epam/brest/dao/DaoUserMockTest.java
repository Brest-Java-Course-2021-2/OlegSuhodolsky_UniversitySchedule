package com.epam.brest.dao;

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
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class DaoUserMockTest {

    @InjectMocks
    private DaoUser daoUser;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<User>> captorMapper;

    @Captor
    private ArgumentCaptor<GeneratedKeyHolder> captorKeyHolder;

    @Captor
    private ArgumentCaptor<SqlParameterSource> captorSource;

    @AfterEach
    public void check() {
        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void findAll() {
        String sql = "select";
        ReflectionTestUtils.setField(daoUser, "getFromUserAll", sql);
        User user = new User();
        List<User> list = Collections.singletonList(user);

        Mockito.when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<User>>any()))
                .thenReturn(list);

        List<User> result = daoUser.getAllUsers();

        Mockito.verify(namedParameterJdbcTemplate).query(eq(sql), captorMapper.capture());

        RowMapper<User> mapper = captorMapper.getValue();

        Assertions.assertNotNull(mapper);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertSame(user, result.get(0));
    }


    @Test
    public void getFromUserById() {
        String sql = "get";
        ReflectionTestUtils.setField(daoUser, "getFromUserById", sql);
        int id = 1;
        User user = new User();


        Mockito.when(namedParameterJdbcTemplate.queryForObject(
                any(),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<RowMapper<User>>any())
        ).thenReturn(user);


        User result = daoUser.readUser(id);

        Mockito.verify(namedParameterJdbcTemplate)
                .queryForObject(eq(sql), captorSource.capture(), captorMapper.capture());

        SqlParameterSource source = captorSource.getValue();
        RowMapper<User> mapper = captorMapper.getValue();

        Assertions.assertNotNull(source);
        Assertions.assertNotNull(mapper);

        Assertions.assertNotNull(result);
        Assertions.assertSame(user, result);
    }


    @Test
    public void createNewUser() {
        String sql = "create";
        ReflectionTestUtils.setField(daoUser, "sqlCreateUser", sql);
        //int id = 1;
        User user = new User();
        Integer count = 0;

        Mockito.when(namedParameterJdbcTemplate.update(
                any(),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<KeyHolder>any())
        ).thenReturn(count);


        Integer result = daoUser.writeUser(user);

        Mockito.verify(namedParameterJdbcTemplate)
                .update(eq(sql), captorSource.capture(), captorKeyHolder.capture());

        SqlParameterSource source = captorSource.getValue();
        KeyHolder keyHolder = captorKeyHolder.getValue();

        Assertions.assertNotNull(source);
        Assertions.assertNotNull(keyHolder);
    }

    @Test
    public void deleteUserObject() {
        String sql = "delete";
        ReflectionTestUtils.setField(daoUser, "sqlDeleteUserById", sql);
        int id = 0;
        Mockito.when(namedParameterJdbcTemplate.update(
                any(),
                ArgumentMatchers.<SqlParameterSource>any()))
                .thenReturn(0);

        daoUser.deleteUser(id);

        Mockito.verify(namedParameterJdbcTemplate)
                .update(eq(sql), captorSource.capture());

        SqlParameterSource source = captorSource.getValue();
        Assertions.assertNotNull(source);
    }

    @Test
    public void updateUserFields() {
        String sql = "update";
        ReflectionTestUtils.setField(daoUser, "sqlUpdateUser", sql);

        User user = new User();
        Integer count = 0;

        Mockito.when(namedParameterJdbcTemplate.update(
                any(),
                ArgumentMatchers.<SqlParameterSource>any(),
                ArgumentMatchers.<KeyHolder>any())
        ).thenReturn(count);

        Integer result = daoUser.updateUser(user);

        Mockito.verify(namedParameterJdbcTemplate)
                .update(eq(sql), captorSource.capture(), captorKeyHolder.capture());

        SqlParameterSource source = captorSource.getValue();
        KeyHolder keyHolder = captorKeyHolder.getValue();

        Assertions.assertNotNull(source);
        Assertions.assertNotNull(keyHolder);
    }


}
