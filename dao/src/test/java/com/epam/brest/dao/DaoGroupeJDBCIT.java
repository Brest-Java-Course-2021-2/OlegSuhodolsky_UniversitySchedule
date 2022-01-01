package com.epam.brest.dao;


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
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback
public class DaoGroupeJDBCIT {

    private final Logger logger = LogManager.getLogger(DaoGroupeJDBCIT.class);

    private DaoGroupe daoGroupe;

    public DaoGroupeJDBCIT(@Autowired DaoGroupe daoGroupe) {
        this.daoGroupe = daoGroupe;
    }

    @Test
    void testFindAll() {
        logger.debug("Execute test: getAllGroupesByName()");
        assertNotNull(daoGroupe);
        assertNotNull(daoGroupe.getGroupesByName());
        assertTrue(daoGroupe.getGroupesByName().size() == 8, "Size = 8");
    }

}
