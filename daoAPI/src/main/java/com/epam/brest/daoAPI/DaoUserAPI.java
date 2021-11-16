package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.User;

import java.util.List;

public interface DaoUserAPI {
    List<User> getAll();

    void read(int id);

    Integer write(User user);

    void update(User user);

    void delete(User user);
}
