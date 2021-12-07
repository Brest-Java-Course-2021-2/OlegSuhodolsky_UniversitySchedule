package com.epam.brest.rest;

import com.epam.brest.dao.DaoUser;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserServiceController {

    private static final Logger logger = LogManager.getLogger(DaoUser.class);

        private final UserService userService;

    public UserServiceController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public final Collection<User> getAllUsers() {

        logger.debug("users(all) ");
        return userService.getAllUsersService();
    }

    @GetMapping(value = "/users/{id}")
    public final User getUserById(@PathVariable Integer id) {

        logger.debug("getUserByID(id) ");
        return userService.getUserByIdService(id);
    }

    @PostMapping(path = "/userscreate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {

        logger.debug("createUser({})", user);
        Integer id = userService.createUserService(user);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/usersupdate", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateDepartment(@RequestBody User user) {

        logger.debug("updateDepartment({})", user);
        int result = userService.updateUserService(user);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/usersdelete/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteDepartment(@PathVariable Integer id) {
        logger.debug("deleteUser({})", id);
        int result = userService.deleteUserService(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
