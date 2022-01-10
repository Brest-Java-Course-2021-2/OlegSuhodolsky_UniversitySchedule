package com.epam.brest.service;

import com.epam.brest.dao.DaoSerialization;
import com.epam.brest.serviceAPI.SerializationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InvalidObjectException;
import java.util.List;

@Component
public class SerializationServiceImpl implements SerializationService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private DaoSerialization daoSerialization;


    public SerializationServiceImpl(DaoSerialization daoSerialization) {
        this.daoSerialization = daoSerialization;
    }

    @Override
    @Transactional (readOnly = true)
    public void getListUserService() throws InvalidObjectException {
        logger.info("Read ALLUsers from file {} return List<User> ");
        daoSerialization.getListUser();
    }

    @Override
    @Transactional
    public boolean writeListUserService() throws InvalidObjectException {
        logger.info("Write ALLUsers to the file {} return true");
        return daoSerialization.writeListUser();
    }

    @Override
    @Transactional (readOnly = true)
    public void getListRequestService() throws InvalidObjectException {
        logger.info("Read ALLRequests from file {} return List<Request> ");
        daoSerialization.getListRequest();
    }

    @Override
    @Transactional
    public boolean writeListRequestService() throws InvalidObjectException {
        logger.info("Write ALLRequests to the file {} return true");
        return daoSerialization.writeListRequest();
    }
}
