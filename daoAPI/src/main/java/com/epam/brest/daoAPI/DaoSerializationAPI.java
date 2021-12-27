package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;

import java.io.InvalidObjectException;
import java.util.List;

public interface DaoSerializationAPI {

    List<User> getListUser () throws InvalidObjectException;

    boolean writeListUser () throws InvalidObjectException;

    List <Request> getListRequest () throws InvalidObjectException;

    boolean writeListRequest () throws InvalidObjectException;
}
