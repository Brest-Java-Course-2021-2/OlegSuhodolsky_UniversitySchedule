package com.epam.brest.service;

import com.epam.brest.dao.DaoRequest;
import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestServiceImpl implements RequestService {
    private final Logger logger = LogManager.getLogger(RequestServiceImpl.class);

    private final DaoRequest daoRequest;

    public RequestServiceImpl(DaoRequest daoRequest) {
        this.daoRequest = daoRequest;
    }


    @Override
    public Request getRequestByIdService(Integer requestId) {
        return null;
    }

    @Override
    public Integer createRequestService(Request request) {
        return null;
    }

    @Override
    public Integer updateRequestService(Request request) {
        return null;
    }

    @Override
    public Integer deleteRequestService(Integer requestId) {
        return null;
    }

    @Override
    public Integer countRequestService() {
        return null;
    }
}
