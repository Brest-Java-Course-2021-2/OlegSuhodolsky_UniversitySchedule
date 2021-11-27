package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.Request;

import java.util.List;

public interface DaoRequestAPI {

    List<Request> getAllRequests(int id);

    Request readRequest(int id, int idE);

    Integer writeRequest(Request request);

    void updateRequest(Request request);

    void deleteRequest(int id, int idR);

}
