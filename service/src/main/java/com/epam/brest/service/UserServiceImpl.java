package com.epam.brest.service;

import com.epam.brest.dao.DaoUser;
import com.epam.brest.daoAPI.DaoGroupeAPI;
import com.epam.brest.daoAPI.DaoRequestAPI;
import com.epam.brest.daoAPI.DaoUserAPI;
import com.epam.brest.model.entity.Groupe;
import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;
import com.epam.brest.service.exceptions.UserNotFoundException;
import com.epam.brest.serviceAPI.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final DaoUserAPI daoUser;

    @Autowired
    private  DaoGroupeAPI daoGroupe;
    @Autowired
    private DaoRequestAPI daoRequest;

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

        Integer count = daoUser.writeUser(user);

        List <User> users = daoUser.getAllUsers();
        for (User u : users){
            if (user.getName() == u.getName()){
                user.setId(u.getId());
                break;
            }
        }
        List<Groupe> groupes = daoGroupe.getGroupesByName();

        for(Groupe groupe : groupes){
            Request request = new Request(user.getId(), groupe.getNameGroupe(), "0", "");
            daoRequest.writeRequest(request);
        }




        return count;
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
