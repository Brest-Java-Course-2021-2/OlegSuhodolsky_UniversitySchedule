package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsersService();

    User getUserByIdService(Integer userId);

    Integer createUserService(User user);

    Integer updateUserService(User user);

    Integer deleteUserService(Integer userId);

    Integer countUserService();
}
