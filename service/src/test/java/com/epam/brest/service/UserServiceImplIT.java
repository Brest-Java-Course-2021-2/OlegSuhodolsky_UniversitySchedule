package com.epam.brest.service;

import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
public class UserServiceImplIT {
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCount() {
        logger.info("Read count of User {}");
        assertNotNull(userService);
        Integer quantity = userService.countUserService();
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertTrue(Integer.valueOf(2) != quantity);
    }

    @Test
    void shouldAllUsers() {
        logger.info("Read All Users {} LIST:");
        List<User> users = userService.getAllUsersService();
        assertNotNull(users);
        if (users.size() > 0) {
            assertTrue(users.size() == userService.countUserService());
        }
    }

    @Test
    void shouldUser() {
        logger.info("Read User {id} ");
        User user = userService.getUserByIdService(1);
        User user1 = userService.getUserByIdService(1);
        assertEquals(user.getName(), user1.getName());
    }

    @Test
    void shouldCreateUser() {
        logger.info("Create User {} ");
        User user = new User("Johnny", "johhny", "1111", "isocrol@yandex.ru");
        Integer count = userService.getAllUsersService().size();
        assertTrue(userService.createUserService(user) > count);
        assertTrue(userService.getAllUsersService().size() > count);
    }

    @Test
    void shouldUpdateUser() {
        logger.info("Read User {id} ");
        User user = userService.getUserByIdService(1);
        user.setName("oleg");
        userService.updateUserService(user);
        assertEquals(userService.getUserByIdService(1).getName(), "oleg");
    }

    @Test
    void shouldDeleteUser() {
        logger.info("Delete User {id} ");
        int count = userService.countUserService();
        userService.deleteUserService(1);
        int count1 = userService.countUserService();
        assertFalse(count1 == count);

    }
}
