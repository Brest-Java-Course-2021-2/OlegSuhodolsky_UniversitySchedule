package com.epam.brest.service;


import com.epam.brest.serviceAPI.GroupeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
public class GroupeServiceImplTest {
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    GroupeService groupeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCount() {
        logger.info("Read count of Groupes and noNull {}");
        assertNotNull(groupeService);
        Integer quantity = groupeService.getAllGroupesByNameService().size();
        assertNotNull(quantity);
        assertTrue(quantity == 8, "size = 8");
   }

}
