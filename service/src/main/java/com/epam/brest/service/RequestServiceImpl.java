package com.epam.brest.service;

import com.epam.brest.dao.DaoRequest;
import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    private final Logger logger = LogManager.getLogger(RequestServiceImpl.class);

    private final DaoRequest daoRequest;

    public RequestServiceImpl(DaoRequest daoRequest) {
        this.daoRequest = daoRequest;
    }


    @Override
    public List<Request> getAllRequestsByIdService(int id) {
        logger.debug("All requests {list<Requests>}");
        return daoRequest.getAllRequests(id);
    }

    @Override
    public Request getRequestByIdService(Integer id, Integer idR) {
        logger.debug("Request {id} {idR}");
        return daoRequest.readRequest(id, idR);
    }

    @Override
    public Integer createRequestService(Request request) {
        logger.debug("Create Request {request}");
        return daoRequest.writeRequest(request);
    }

    @Override
    public void updateRequestService(Request request) {
        logger.debug("Update Request {request}");
        daoRequest.updateRequest(request);
    }

    @Override
    public void deleteRequestService(Integer id, Integer idR) {
        daoRequest.deleteRequest(id, idR);
    }

    @Override
    public Integer countRequestService(int id) {
        logger.debug("count()");
        return this.daoRequest.getAllRequests(id).size();
       }
}
