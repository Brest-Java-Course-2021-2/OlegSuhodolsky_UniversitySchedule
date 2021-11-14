package com.epam.brest.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
class DaoUserJDBCIT {

    private final Logger logger = LogManager.getLogger(DaoUserJDBCIT.class);

    private DaoUser daoUser;

    public DaoUserJDBCIT(@Autowired DaoUser daoUser) {
        this.daoUser =  daoUser;
    }

    @Test
    void findAll() {
        logger.debug("Execute test: getAll()");
        assertNotNull(daoUser);
        assertNotNull(daoUser.getAll());
    }



}
