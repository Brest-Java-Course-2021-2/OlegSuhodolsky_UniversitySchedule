package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.Request;

import java.util.List;

public interface DaoRequestAPI {

    List<Request> getAll() ;

    void read(int id);

    void write(Request request);

    void update(Request request);

    void delete(Request request);

}
