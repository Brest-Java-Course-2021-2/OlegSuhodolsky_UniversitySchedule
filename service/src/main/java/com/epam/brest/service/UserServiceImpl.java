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
    public List<User> getAllUsers() {
        logger.info("Read ALLUsers {} return List<User> ");
        return daoUser.getAll();
    }

    @Override
    public User getUserById(Integer id) {
        logger.info("Read User {id}");
        User user = null;
        user = daoUser.read(id);
        return user;
    }

    @Override
    public Integer create(User user) {
        logger.info("Create User {}");
        return daoUser.write(user);
    }

    @Override
    public Integer update(User user) {
        logger.info("Update User {}");
        return daoUser.updateUser(user);
    }

    @Override
    public void delete(Integer id) {
        daoUser.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count() {
        logger.debug("count()");
        return this.daoUser.getAll().size();
    }
}
