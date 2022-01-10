package com.epam.brest.dao;


import com.epam.brest.model.entity.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@Rollback
public class DaoRequestJDBCIT {

    private final Logger logger = LogManager.getLogger(DaoUserJDBCIT.class);

    private DaoRequest daoRequest;

    public DaoRequestJDBCIT(@Autowired DaoRequest daoRequest) {
        this.daoRequest = daoRequest;
    }

    @Test
    void findAll() {
        logger.debug("Execute test of REQUEST : getAll()");
        assertNotNull(daoRequest);
        assertNotNull(daoRequest.getAllRequests(1));
        assertTrue(daoRequest.getAllRequests(1).size() > 0, "Size > 0");
    }

    @Test
    void insertRequest() {
        logger.debug("Execute test of REQUEST : write({id})");
        Request request = new Request(1, "e1", "2", "sport", new Date());
        assertTrue(daoRequest.writeRequest(request) > 0, "Size > 0 ");
    }

    @Test
    void readRequest() {
        logger.debug("Execute test of REQUEST : read({id}{idR})");
        assertTrue(daoRequest.readRequest(1).getSubject().equals("math"), "subject = math");
    }

    @Test
    void deleteRequest() {
        logger.debug("Execute test of REQUEST : delete({id}{idR})");
        daoRequest.deleteRequest(1);
        assertTrue(daoRequest.getAllRequests(1).get(0).getIdR() != 1, "request not exist, deleted");
    }

    @Test
    void updateRequest() {
        logger.debug("Execute test of REQUEST : update({request})");
        Request request = new Request(1, 1, "e22", "22", "cockinjaws", new Date());
        Date date = request.getDate();
        daoRequest.updateRequest(request);
        assertTrue(daoRequest.readRequest(1).getGroupe().equals("e22"), "request Groupe changed");
    }
}
