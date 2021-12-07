package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.User;

import java.util.List;

public interface UserService {
    /**
     * Find all users.
     *
     * @return User list.
     */
    List<User> getAllUsersService();

    /**
     * Find user by id .
     *
     * @return User.
     */
    User getUserByIdService(Integer userId);

    /**
     * Insert new User.
     *
     * @return persisted user id.
     */
    Integer createUserService(User user);

    /**
     * Update User.
     *
     * @return persisted user id.
     */
    Integer updateUserService(User user);

    /**
     * Delete User.
     *
     * @return number of updates users in the database.
     */
    Integer deleteUserService(Integer userId);

    /**
     * Count od users.
     *
     * @return quantity of the users.
     */
    Integer countUserService();
}
