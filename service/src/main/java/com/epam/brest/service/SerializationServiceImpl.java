package com.epam.brest.service;

import com.epam.brest.dao.DaoSerialization;
import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.SerializationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InvalidObjectException;
import java.util.List;

public class SerializationServiceImpl implements SerializationService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private DaoSerialization daoSerialization;

    @Autowired
    public SerializationServiceImpl(DaoSerialization daoSerialization) {
        this.daoSerialization = daoSerialization;
    }

    @Override
    public List<User> getListUserService() throws InvalidObjectException {
        logger.info("Read ALLUsers from file {} return List<User> ");
        return (List<User>) daoSerialization.getListUser();
    }

    @Override
    public boolean writeListUserService() throws InvalidObjectException {
        logger.info("Write ALLUsers to the file {} return true");
        return daoSerialization.writeListUser();
    }

    @Override
    public List<Request> getListRequestService() throws InvalidObjectException {
        logger.info("Read ALLRequests from file {} return List<Request> ");
        return (List<Request>) daoSerialization.getListRequest();
    }

    @Override
    public boolean writeListRequestService() throws InvalidObjectException {
        logger.info("Write ALLRequests to the file {} return true");
        return daoSerialization.writeListRequest();
    }
}
