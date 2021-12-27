package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;

import java.io.InvalidObjectException;
import java.util.List;

public interface SerializationService {

   void getListUserService () throws InvalidObjectException;

    boolean writeListUserService () throws InvalidObjectException;

    void getListRequestService () throws InvalidObjectException;

    boolean writeListRequestService () throws InvalidObjectException;
}
