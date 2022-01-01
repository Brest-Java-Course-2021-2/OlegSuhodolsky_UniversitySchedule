package com.epam.brest.service;

import com.epam.brest.daoAPI.DaoGroupeAPI;
import com.epam.brest.model.entity.Groupe;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.GroupeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GroupeServiceImpl implements GroupeService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final DaoGroupeAPI daoGroupe;

    public GroupeServiceImpl(DaoGroupeAPI daoGroupe) {
        this.daoGroupe = daoGroupe;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Groupe> getAllGroupesByNameService() {
        logger.info("Read ALLGroupes {} return List<Groupe> ");
        return daoGroupe.getGroupesByName();
    }
}
