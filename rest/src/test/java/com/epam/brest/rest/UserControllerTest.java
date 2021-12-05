package com.epam.brest.rest;


import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class UserControllerTest {

    @Autowired
    private UserServiceController userServiceController;
    @Autowired
    UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();


    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(userServiceController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
//                .setControllerAdvice(customExceptionHandler)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }


    @Test
    public void shouldGetUserById(){
        User user = new User(RandomStringUtils.randomAlphabetic(50)
                , RandomStringUtils.randomAlphabetic(50)
                ,RandomStringUtils.randomAlphabetic(50)
                ,RandomStringUtils.randomAlphabetic(50));
        Integer id = userService.createUserService(user);

        List<User> users = userService.getAllUsersService();

        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

}
