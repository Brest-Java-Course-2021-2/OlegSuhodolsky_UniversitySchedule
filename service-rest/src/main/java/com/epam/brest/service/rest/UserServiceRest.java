package com.epam.brest.service.rest;

import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceRest implements UserService {

    private final Logger logger = LogManager.getLogger(UserServiceRest.class);

    private String url;

    private RestTemplate restTemplate;

    public UserServiceRest() {
    }

    public UserServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }


    @Override
    public List<User> getAllUsersService() {
        logger.debug("getAllUsersService()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<User>) responseEntity.getBody();
    }

    @Override
    public User getUserByIdService(Integer userId) {
        logger.debug("getUserById({})", userId);
        ResponseEntity<User> responseEntity =
                restTemplate.getForEntity(url + "/" + userId, User.class);
        return responseEntity.getBody();
    }

    @Override
    public Integer createUserService(User user) {
        logger.debug("createUser({})", user);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, user, Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public Integer updateUserService(User user) {
        logger.debug("updateUser({})", user);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<Integer> result = restTemplate.exchange(url, HttpMethod.PUT, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer deleteUserService(Integer userId) {
        logger.debug("deleteUser({})", userId);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<Integer> result =
                restTemplate.exchange(url + "/" + userId, HttpMethod.DELETE, entity, Integer.class);
        return result.getBody();
    }

    @Override
    public Integer countUserService() {
        logger.debug("count()");
        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(url + "/count" , Integer.class);
        return responseEntity.getBody();
    }
}
