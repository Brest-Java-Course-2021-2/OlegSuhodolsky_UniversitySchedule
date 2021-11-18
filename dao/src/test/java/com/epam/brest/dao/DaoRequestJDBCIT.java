package com.epam.brest.dao;


import com.epam.brest.model.entity.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
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
        assertNotNull(daoRequest.getAll(1));
        assertTrue(daoRequest.getAll(1).size() == 2, "Size = 2");
    }

    @Test
    void insertRequest() {
        logger.debug("Execute test of REQUEST : write({id})");
        Request request = new Request(2, "e1", "2", "sport");
        assertTrue(daoRequest.write(request) == 3, "Size = 1");
    }


}
