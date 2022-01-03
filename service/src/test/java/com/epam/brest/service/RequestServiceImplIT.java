package com.epam.brest.service;


import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
public class RequestServiceImplIT {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    RequestService requestService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCount() {
        logger.info("Read count of Request {}");
        assertNotNull(requestService);
        Integer quantity = requestService.countRequestService(1);
        assertNotNull(quantity);
        assertTrue(quantity > 0);
        assertTrue(Integer.valueOf(2) != quantity);
    }

    @Test
    void shouldAllRequests() {
        logger.info("Read All Requests {} LIST:");
        List<Request> requests = requestService.getAllRequestsByIdService(1);
        assertNotNull(requests);
        if (requests.size() > 0) {
            assertTrue(requests.size() == requestService.countRequestService(1));
        }
    }

    @Test
    void shouldRequest() {
        logger.info("Read Request {id}{idR} ");
        Request request = requestService.getRequestByIdService(1);
        Request request1 = requestService.getRequestByIdService(1);
        assertNotNull(request);
        assertNotNull(request1);
        assertEquals(request.getGroupe(), request1.getGroupe());
    }


    @Test
    void shouldCreateRequest() {
        logger.info("Create Request {request} ");
        Request request = new Request(2,"e1", "2", "history", new Date());
        Integer count = requestService.getAllRequestsByIdService(2).size();
        assertTrue(requestService.createRequestService(request) > count);
     }

    @Test
    void shouldUpdateRequest() {
        logger.info("Update Request {id} ");
        Request request = requestService.getRequestByIdService(1);
        request.setGroupe("e222");
        requestService.updateRequestService(request);
        assertEquals(requestService.getRequestByIdService(1).getGroupe(), "e222");
    }

    @Test
    void shouldDeleteRequest() {
        logger.info("Delete Request {id} ");
        int count = requestService.getAllRequestsByIdService(1).size();
        requestService.deleteRequestService(1);
        int count1 = requestService.getAllRequestsByIdService(1).size();
        assertTrue(count1 < count);
    }
}
