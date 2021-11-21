package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer create(User user);

    Integer update(User user);

    Integer delete(Integer userId);

    Integer count();
}
