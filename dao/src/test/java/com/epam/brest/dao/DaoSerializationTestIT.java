package com.epam.brest.dao;


import com.epam.brest.Serializator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Rollback
public class DaoSerializationTestIT {

    private final Logger logger = LogManager.getLogger(DaoSerializationTestIT.class);

    private DaoUser daoUser;
    private DaoRequest daoRequest;
    private Serializator serializator;
    private DaoSerialization daoSerialization;

    String fileUser = "userTest.sql";
    String fileRequest = "requestTest.sql";

    @Autowired
    public DaoSerializationTestIT(DaoSerialization daoSerialization, DaoUser daoUser,
                                  DaoRequest daoRequest, Serializator serializator) {
        this.daoSerialization = daoSerialization;
        this.daoUser = daoUser;
        this.daoRequest = daoRequest;
        this.serializator = serializator;
    }

    @Test
    void testFindBeans() {
        logger.debug("Execute test: FindAllBeans");
        assertNotNull(daoUser);
        assertNotNull(daoRequest);
        assertNotNull(daoSerialization);
        assertNotNull(serializator);
    }

    @Test
    void testFindAllUsers() {
        logger.debug("Execute test: FindAllUsers()");
        assertNotNull(daoUser.getAllUsers());
        assertTrue(daoUser.getAllUsers().size() > 0, "Size > 0");
    }

    @Test
    void testFindAllRequests() {
        logger.debug("Execute test: FindAllRequests()");
        assertNotNull(daoRequest.getAllRequests(1));
        assertTrue(daoRequest.getAllRequests(1).size() > 0, "Size > 0");
    }

}
