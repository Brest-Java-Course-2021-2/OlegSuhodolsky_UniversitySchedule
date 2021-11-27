package com.epam.brest.dao;


import com.epam.brest.model.entity.Request;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class DaorequestMockTest {

    @InjectMocks
    private DaoRequest daoRequest;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Request>> captorMapper;

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
        ReflectionTestUtils.setField(daoRequest, "getFromRequestAll", sql);
        Request request = new Request();
        List<Request> list = Collections.singletonList(request);

        Mockito.when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<SqlParameterSource>any(),
                        ArgumentMatchers.<RowMapper<Request>>any()))
                .thenReturn(list);
        int id = 0;
        List<Request> result = daoRequest.getAllRequests(id);

        Mockito.verify(namedParameterJdbcTemplate).query(eq(sql),captorSource.capture(), captorMapper.capture());

        RowMapper<Request> mapper = captorMapper.getValue();

        Assertions.assertNotNull(mapper);
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertSame(request, result.get(0));
    }


}
