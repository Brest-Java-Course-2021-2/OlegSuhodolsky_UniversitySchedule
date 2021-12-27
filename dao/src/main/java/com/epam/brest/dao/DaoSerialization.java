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

    /*private List <User> listUser = new ArrayList<>();
    private List <Request> listRequest = new ArrayList<>();
*/
    String fileUser = "user.sql";
    String fileRequest = "request.sql";


   // @Autowired
    public DaoSerialization(Serializator serializator, DaoUser daoUser, DaoRequest daoRequest) {
            this.serializator = serializator;
            this.daoUser = daoUser;
            this.daoRequest = daoRequest;
    }

    @Override
   public void getListUser () throws InvalidObjectException {
        List <User> listUser = (List <User>) serializator.deserialization(fileUser);
        for(User user : listUser){
            user.setId(0);
            daoUser.writeUser(user);
        }
     }

@Override
    public boolean writeListUser () throws InvalidObjectException {
        List<User> listUser = daoUser.getAllUsers();
        return serializator.serialization(listUser, fileUser);
    }

@Override
    public void getListRequest () throws InvalidObjectException {
        List <Request> listRequest = (List<Request>) serializator.deserialization(fileRequest);
        for(Request request : listRequest){
            request.setIdR(0);
            daoRequest.writeRequest(request);
        }
     }

@Override
    public boolean writeListRequest () throws InvalidObjectException {
        List<User> listUser = daoUser.getAllUsers();
        List <Request> listRequest = new ArrayList<>();
        for (User u : listUser) {
        List <Request> listRequest1 =  daoRequest.getAllRequests(u.getId());
        if (listRequest1.size() > 0){
        listRequest.addAll(listRequest1);}
        }
        return serializator.serialization(listRequest, fileRequest);
    }



}
