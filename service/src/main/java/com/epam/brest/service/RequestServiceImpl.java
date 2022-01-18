package com.epam.brest.service;

import com.epam.brest.dao.DaoRequest;
import com.epam.brest.daoAPI.DaoRequestAPI;
import com.epam.brest.model.entity.Request;
import com.epam.brest.service.exceptions.RequestNotFoundException;
import com.epam.brest.serviceAPI.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RequestServiceImpl implements RequestService {

    private final Logger logger = LogManager.getLogger(RequestServiceImpl.class);
    private final DaoRequestAPI daoRequest;

    public RequestServiceImpl(DaoRequest daoRequest) {
        this.daoRequest = daoRequest;
    }

    @Override
    @Transactional (readOnly = true)
    public List<Request> getAllRequestsByIdService(int id) {
        logger.debug("All requests {list<Requests>}");
        return daoRequest.getAllRequests(id);
    }

    @Override
    @Transactional (readOnly = true)
    public Request getRequestByIdService(Integer idR) {
        logger.debug("Request {id} {idR}");
        try{
        return daoRequest.readRequest(idR);
        } catch (RequestNotFoundException e){
            throw new RequestNotFoundException(idR);
        }
    }

    @Override
    @Transactional
    public Integer createRequestService(Request request) {
        logger.debug("Create Request {request}");
        return daoRequest.writeRequest(request);
    }

    @Override
    @Transactional
    public Integer updateRequestService(Request request) {
        logger.debug("Update Request {request}");
       return daoRequest.updateRequest(request);
    }

    @Override
    @Transactional
    public Integer deleteRequestService(Integer idR) {
        logger.debug("Delete Request {idR}" + idR);
        Request request = daoRequest.readRequest(idR);
        request.setPairs("0");
        request.setSubject("");
        daoRequest.updateRequest(request);
        /*return daoRequest.deleteRequest(idR);*/
        return 1;
    }

    @Override
    @Transactional (readOnly = true)
    public Integer countRequestService(int id) {
        logger.debug("count()");
        return this.daoRequest.getAllRequests(id).size();
       }
}
