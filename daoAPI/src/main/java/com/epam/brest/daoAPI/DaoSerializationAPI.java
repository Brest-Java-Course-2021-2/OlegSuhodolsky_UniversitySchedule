package com.epam.brest.daoAPI;

import java.io.InvalidObjectException;

public interface DaoSerializationAPI {

    void getListUser() throws InvalidObjectException;

    boolean writeListUser() throws InvalidObjectException;

    void getListRequest() throws InvalidObjectException;

    boolean writeListRequest() throws InvalidObjectException;
}
