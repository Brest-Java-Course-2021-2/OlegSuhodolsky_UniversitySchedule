package com.epam.brest.service.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;

public class UserNotFoundException extends EmptyResultDataAccessException {
    public UserNotFoundException(Integer id)
    {
        super("User not found for id: " + id, 1);
    }
}
