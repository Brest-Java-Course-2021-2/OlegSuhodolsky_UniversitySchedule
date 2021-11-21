package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.Request;

public interface RequestService {

    Request getRequestById(Integer requestId);

    Integer create(Request request);

    Integer update(Request request);

    Integer delete(Integer requestId);

    Integer count();
}
