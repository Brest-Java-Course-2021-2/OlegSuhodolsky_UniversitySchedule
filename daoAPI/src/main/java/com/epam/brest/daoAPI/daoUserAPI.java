package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.User;

import java.util.List;

public interface daoUserAPI  {
    List<User> detAll();
    void read(int id);
    void write(User user);
    void update(User user);
    void delete(User user);
}
