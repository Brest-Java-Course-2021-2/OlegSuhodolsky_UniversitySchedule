package com.epam.brest.service;

import com.epam.brest.dao.DaoUser;
import com.epam.brest.daoAPI.DaoUserAPI;
import com.epam.brest.model.entity.User;
import com.epam.brest.service.exceptions.UserNotFoundException;
import com.epam.brest.serviceAPI.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class UserServiceImpl implements UserService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final DaoUserAPI daoUser;

    public UserServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    @Transactional (readOnly = true)
    public List<User> getAllUsersService() {
        logger.info("Read ALLUsers {} return List<User> ");
        return daoUser.getAllUsers();
    }

    @Override
    @Transactional (readOnly = true)
    public User getUserByIdService(Integer id) {
        logger.info("Read User {id}");
       try{
        return daoUser.readUser(id);
       }catch (EmptyResultDataAccessException e){
           throw new UserNotFoundException(id);
       }
    }

    @Override
    @Transactional
    public Integer createUserService(User user) {
        logger.info("Create User {}");
        return daoUser.writeUser(user);
    }


    @Override
    @Transactional
    public Integer updateUserService(User user) {
        logger.info("Update User {}");
        return daoUser.updateUser(user);
    }

    @Override
    @Transactional
    public Integer deleteUserService(Integer id) {
        return daoUser.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countUserService() {
        logger.debug("count()");
        return this.daoUser.getAllUsers().size();
    }
}
