package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Integer userId);

    Integer create(User user);

    Integer update(User user);

    void delete(Integer userId);

    Integer count();
}
