package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;

import java.io.InvalidObjectException;
import java.util.List;

public interface DaoSerializationAPI {

    void getListUser () throws InvalidObjectException;

    boolean writeListUser () throws InvalidObjectException;

    void getListRequest () throws InvalidObjectException;

    boolean writeListRequest () throws InvalidObjectException;
}
