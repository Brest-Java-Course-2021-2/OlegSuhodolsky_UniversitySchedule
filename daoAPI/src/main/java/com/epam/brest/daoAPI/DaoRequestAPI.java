package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.Request;

import java.util.List;

public interface DaoRequestAPI {

    List<Request> getAllRequests(int id);

    Request readRequest(int id, int idE);

    Integer writeRequest(Request request);

    Integer updateRequest(Request request);

    Integer deleteRequest(int id, int idR);

}
