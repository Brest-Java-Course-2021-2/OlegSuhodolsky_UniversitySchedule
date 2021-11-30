package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.Request;

import java.util.List;

public interface RequestService {

    List<Request> getAllRequestsByIdService(int id);
    Request getRequestByIdService(Integer requestId, Integer idR);

    Integer createRequestService(Request request);

    void updateRequestService(Request request);

    void deleteRequestService(Integer requestId, Integer idR);

    Integer countRequestService(int id);
}
