package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.Request;

import java.util.List;

public interface DaoRequestAPI {

    List<Request> getAllRequests(int id);

    Request readRequest(int idR);

    Integer writeRequest(Request request);

    Integer updateRequest(Request request);

    Integer deleteRequest(int idR);

}
