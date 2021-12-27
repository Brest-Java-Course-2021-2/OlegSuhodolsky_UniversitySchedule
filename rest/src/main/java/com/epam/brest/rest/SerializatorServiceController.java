package com.epam.brest.rest;


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
    private static final Logger logger = LogManager.getLogger(DaoUser.class);
    private SerializationService serializationService;


    public SerializatorServiceController(@Autowired SerializationService serializationService)  {
        this.serializationService = serializationService;
        }

    @GetMapping(value = "/deserializer")
    public void getFromFileAllUsersAndRequests () throws InvalidObjectException {
        serializationService.getListUserService();
        serializationService.getListRequestService();
    }

    @GetMapping(value = "/serializator")
    public final boolean writeToFileAllUsersAndRequests() throws InvalidObjectException {
        logger.debug("serialize(all) ");
        boolean bool = serializationService.writeListRequestService();
        boolean bool1 = serializationService.writeListUserService();
        return (bool && bool1);
  }

}
