package com.epam.brest;

import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.RequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RequestServiceRest implements RequestService {

    private final Logger logger = LogManager.getLogger(RequestServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public RequestServiceRest() {
    }

    public RequestServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Request> getAllRequestsByIdService(int id) {
        logger.debug("getAllRequestsByIdService()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/" + id, List.class);
        return (List<Request>) responseEntity.getBody();
    }

    @Override
    public Request getRequestByIdService(Integer idR) {
        return null;
    }


    @Override
    public Integer createRequestService(Request request) {

        logger.debug("createRequest({})", request);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, request, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer updateRequestService(Request request) {
        logger.debug("updateRequest({})", request);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Request> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer deleteRequestService(Integer idR) {
        return null;
    }

    @Override
    public Integer countRequestService(int id) {
        return null;
    }
}
