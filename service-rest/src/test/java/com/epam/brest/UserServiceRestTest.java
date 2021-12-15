package com.epam.brest;




import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import  com.epam.brest.model.entity.User;

import static com.epam.brest.model.entity.constants.UserConstants.USER_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-rest-test.xml"})
public class UserServiceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceRestTest.class);

    public static final String USERS_URL = "http://localhost:8099/users";

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    UserServiceRest userService;


    @BeforeEach
    public void before() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        userService = new UserServiceRest(USERS_URL, restTemplate);
    }

    @Test
    public void shouldFindAllusers() throws Exception {

        LOGGER.debug("shouldFindAllUsers()");
        // given
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(USERS_URL)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(Arrays.asList(create(0), create(1))))
                );


        // when
        List<User> users = userService.getAllUsersService();

        // then
        mockServer.verify();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void shouldCreateUser() throws Exception {

        LOGGER.debug("shouldCreateUser()");
        // given
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(USER_NAME_SIZE));




        mockServer.expect(ExpectedCount.once(), requestTo(new URI(USERS_URL)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("1"))
                );
        // when
        Integer id = userService.createUserService(user);

        // then
        mockServer.verify();
        assertNotNull(id);
    }

    private User create(int index) {
        User user = new User();
        user.setId(index);
        user.setName("d" + index);
        return user;
    }

}
