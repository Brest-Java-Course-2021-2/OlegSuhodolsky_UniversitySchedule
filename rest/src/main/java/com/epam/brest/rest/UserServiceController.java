package com.epam.brest.rest;

import com.epam.brest.dao.DaoUser;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserServiceController {

    private static final Logger logger = LogManager.getLogger(DaoUser.class);

        private final UserService userService;

    public UserServiceController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users/{id}")
    public final User getUserById(@PathVariable Integer id) {

        logger.debug("user(id) ");
        return userService.getUserByIdService(id);
    }
}
