package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.User;

import java.util.List;


public interface DaoUserAPI {
    List<User> getAll();

    List<User> read(int id);

    Integer write(User user);

    Integer updateUser(User user);

    void delete(int id);
}
