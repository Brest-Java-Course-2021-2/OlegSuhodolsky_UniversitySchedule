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
    public Request getRequestById(Integer requestId) {
        return null;
    }

    @Override
    public Integer create(Request request) {
        return null;
    }

    @Override
    public Integer update(Request request) {
        return null;
    }

    @Override
    public Integer delete(Integer requestId) {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }
}
