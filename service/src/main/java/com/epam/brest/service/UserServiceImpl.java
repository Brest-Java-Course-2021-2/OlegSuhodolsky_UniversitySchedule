package com.epam.brest.service;


import com.epam.brest.dao.DaoUser;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class UserServiceImpl implements UserService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final DaoUser daoUser;

    public UserServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public User getUserById(Integer userId) {
        return null;
    }

    @Override
    public Integer create(User user) {
        return null;
    }

    @Override
    public Integer update(User user) {
        return null;
    }

    @Override
    public Integer delete(Integer userId) {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }
}
