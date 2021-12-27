package com.epam.brest.dao;

import com.epam.brest.Serializator;
import com.epam.brest.daoAPI.DaoSerializationAPI;
import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DaoSerialization implements DaoSerializationAPI {

    private final Logger logger = LogManager.getLogger(DaoSerialization.class);
    private Serializator serializator;
    private DaoUser daoUser;
    private DaoRequest daoRequest;

    private List <User> listUser = new ArrayList<>();
    private List <Request> listRequest = new ArrayList<>();

    String fileUser = "user.sql";
    String fileRequest = "request.sql";


    @Autowired
    public DaoSerialization(Serializator serializator, DaoUser daoUser, DaoRequest daoRequest) {
            this.serializator = serializator;
            this.daoUser = daoUser;
            this.daoRequest = daoRequest;
    }

    @Override
   public List <User> getListUser () throws InvalidObjectException {
        listUser = (List <User>) serializator.deserialization(fileUser);
        for(User user : listUser){
            user.setId(0);
            daoUser.writeUser(user);
        }
        return listUser;
   }

@Override
    public boolean writeListUser () throws InvalidObjectException {
        listUser = daoUser.getAllUsers();
        return serializator.serialization(listUser, fileUser);
    }

@Override
    public List <Request> getListRequest () throws InvalidObjectException {
        listRequest = (List<Request>) serializator.deserialization(fileRequest);
        for(Request request : listRequest){
            request.setIdR(0);
            daoRequest.writeRequest(request);
        }
         return listRequest;
    }

@Override
    public boolean writeListRequest () throws InvalidObjectException {
        listUser = daoUser.getAllUsers();
        for (User u : listUser) {
        listRequest.addAll(daoRequest.getAllRequests(u.getId()));
        }
        return serializator.serialization(listRequest, fileRequest);
    }



}
