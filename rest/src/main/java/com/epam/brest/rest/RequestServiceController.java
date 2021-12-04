package com.epam.brest.rest;

import com.epam.brest.dao.DaoRequest;
import com.epam.brest.serviceAPI.RequestService;


public class RequestServiceController {

    private RequestService requestService;

    public RequestServiceController(RequestService requestService) {
        this.requestService = requestService;
    }
}
