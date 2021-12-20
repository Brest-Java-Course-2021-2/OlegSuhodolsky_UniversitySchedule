package com.epam.brest.rest;

import com.epam.brest.dao.DaoRequest;
import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class RequestServiceController {
    private static final Logger logger = LogManager.getLogger(DaoRequest.class);

    private RequestService requestService;

    public RequestServiceController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/requests/{id}")
    public final List<Request> getRequestsById(@PathVariable Integer id) {

        logger.debug("requests(id) ");
        return requestService.getAllRequestsByIdService(id);
    }


    @PostMapping(path = "/requests", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createRequest(@RequestBody Request request) {
        //request.setDate(new Date());
        logger.debug("createRequest({})", request);
        Integer id = requestService.createRequestService(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/requests", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateRequest(@RequestBody Request request) {
        request.setDate(new Date());
        logger.debug("updateRequest({})", request);
        int result = requestService.updateRequestService(request);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/requests/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> deleteRequest(@PathVariable Integer idR) {
        logger.debug("deleteRequest({})", idR );
        int result = requestService.deleteRequestService(idR);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
