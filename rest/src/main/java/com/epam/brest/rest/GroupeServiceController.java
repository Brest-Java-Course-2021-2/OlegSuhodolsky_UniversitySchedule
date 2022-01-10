package com.epam.brest.rest;

import com.epam.brest.dao.DaoGroupe;
import com.epam.brest.model.entity.Groupe;
import com.epam.brest.serviceAPI.GroupeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupeServiceController {

    private static final Logger logger = LogManager.getLogger(DaoGroupe.class);
    private final GroupeService groupeService;

    public GroupeServiceController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @GetMapping(value = "/groupes")
    public final List<Groupe> getGroupes() {
        logger.debug("getGroupes() ");
        return groupeService.getAllGroupesByNameService();
    }
}
