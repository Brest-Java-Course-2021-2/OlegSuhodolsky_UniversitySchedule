package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.Request;

import java.util.List;

public interface DaoRequestAPI {

    List<Request> getAll(int id);

    Request read(int id, int idE);

    Integer write(Request request);

    void update(Request request);

    void delete(int id, int idR);

}
