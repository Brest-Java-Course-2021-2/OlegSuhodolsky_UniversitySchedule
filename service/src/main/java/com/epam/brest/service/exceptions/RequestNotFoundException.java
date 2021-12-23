package com.epam.brest.service.exceptions;
import org.springframework.dao.EmptyResultDataAccessException;
public class RequestNotFoundException extends EmptyResultDataAccessException{
    public RequestNotFoundException(Integer id)
    {
        super("request not found for idR: " + id, 1);
    }

}
