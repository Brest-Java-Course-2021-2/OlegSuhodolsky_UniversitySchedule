package com.epam.brest.rest;


import com.epam.brest.dao.DaoSerialization;
import com.epam.brest.dao.DaoUser;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.SerializationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import java.io.InvalidObjectException;
import java.util.List;

@RestController
public class SerializatorServiceController {
    private static final Logger logger = LogManager.getLogger(DaoSerialization.class);
    private SerializationService serializationService;


    public SerializatorServiceController(@Autowired SerializationService serializationService)  {
        this.serializationService = serializationService;
        }

    @GetMapping(value = "/serializator/deserializer")
        public void getFromFileAllUsersAndRequests () throws InvalidObjectException {
        serializationService.getListUserService();
        serializationService.getListRequestService();
    }

    @GetMapping(value = "/serializator/serializer")
    public final boolean writeToFileAllUsersAndRequests() throws InvalidObjectException {
        logger.debug("serialize(all) ");
        boolean bool1 = serializationService.writeListUserService();
        boolean bool = serializationService.writeListRequestService();
        return (bool && bool1);
  }

}
