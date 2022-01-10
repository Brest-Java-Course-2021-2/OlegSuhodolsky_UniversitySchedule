package com.epam.brest.serviceAPI;

import java.io.InvalidObjectException;

public interface SerializationService {

   void getListUserService () throws InvalidObjectException;

    boolean writeListUserService () throws InvalidObjectException;

    void getListRequestService () throws InvalidObjectException;

    boolean writeListRequestService () throws InvalidObjectException;
}
