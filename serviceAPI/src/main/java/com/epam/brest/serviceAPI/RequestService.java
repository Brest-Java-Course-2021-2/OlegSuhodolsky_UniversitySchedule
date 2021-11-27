package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.Request;

public interface RequestService {

    Request getRequestByIdService(Integer requestId);

    Integer createRequestService(Request request);

    Integer updateRequestService(Request request);

    Integer deleteRequestService(Integer requestId);

    Integer countRequestService();
}
