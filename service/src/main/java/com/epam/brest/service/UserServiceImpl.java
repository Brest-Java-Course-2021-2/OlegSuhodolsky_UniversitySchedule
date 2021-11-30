package com.epam.brest.service;

import com.epam.brest.dao.DaoUser;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class UserServiceImpl implements UserService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final DaoUser daoUser;

    public UserServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public List<User> getAllUsersService() {
        logger.info("Read ALLUsers {} return List<User> ");
        return daoUser.getAllUsers();
    }

    @Override
    public User getUserByIdService(Integer id) {
        logger.info("Read User {id}");
        User user = null;
        user = daoUser.readUser(id);
        return user;
    }

    @Override
    public Integer createUserService(User user) {
        logger.info("Create User {}");
        return daoUser.writeUser(user);
    }

    @Override
    public Integer updateUserService(User user) {
        logger.info("Update User {}");
        return daoUser.updateUser(user);
    }

    @Override
    public int deleteUserService(Integer id) {
        return daoUser.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countUserService() {
        logger.debug("count()");
        return this.daoUser.getAllUsers().size();
    }
}
