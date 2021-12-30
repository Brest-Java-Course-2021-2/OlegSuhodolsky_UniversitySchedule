package com.epam.brest.service.rest;

import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.SerializationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.InvalidObjectException;
import java.util.List;

public class SerializatorServiceRest implements SerializationService {

    private final Logger logger = LogManager.getLogger(RequestServiceRest.class);

    private String url;
    private RestTemplate restTemplate;

    public SerializatorServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public void getListUserService() throws InvalidObjectException {
        logger.debug("WRITE TO DATABASE FROM THE FILES()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/deserializer" , Boolean.class);
        //return (boolean) responseEntity.getBody();
    }

    @Override
    public boolean writeListUserService() throws InvalidObjectException {
        logger.debug("WRITE TO FILE()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/serializer" , Boolean.class);
        return (boolean) responseEntity.getBody();
    }

    @Override
    public void getListRequestService() throws InvalidObjectException {

    }

    @Override
    public boolean writeListRequestService() throws InvalidObjectException {
        return false;
    }
}
