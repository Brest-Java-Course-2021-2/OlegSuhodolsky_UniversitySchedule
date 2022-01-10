package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.User;

import java.util.List;

public interface DaoUserAPI {

    List<User> getAllUsers();

    User readUser(int id);

    Integer writeUser(User user);

    Integer updateUser(User user);

    int deleteUser(int id);
}
