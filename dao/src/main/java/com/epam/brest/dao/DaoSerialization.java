package com.epam.brest.dao;

import com.epam.brest.Serializator;
import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DaoSerialization {

    private final Logger logger = LogManager.getLogger(DaoSerialization.class);
    private Serializator serializator;
    private DaoUser daoUser;
    private DaoRequest daoRequest;

    private List <User> listUser = new ArrayList<>();
    private List <Request> listRequest = new ArrayList<>();

    String fileUser = "user.sql";
    String fileRequest = "request.sql";
    String fileAdmin = "admin.sql";
    String fileGroupe = "groupe.sql";

    @Autowired
    public DaoSerialization(Serializator serializator, DaoUser daoUser, DaoRequest daoRequest) {
            this.serializator = serializator;
            this.daoUser = daoUser;
            this.daoRequest = daoRequest;
    }

   public List <User> getListUser () throws InvalidObjectException {
        listUser = serializator.deserialization(fileUser);
        for(User user : listUser){
            user.setId(0);
            daoUser.writeUser(user);
        }
        return listUser;
   }

    public boolean writeListUser (List listUser) throws InvalidObjectException {
    return serializator.serialization(listUser, fileUser);
    }


}
