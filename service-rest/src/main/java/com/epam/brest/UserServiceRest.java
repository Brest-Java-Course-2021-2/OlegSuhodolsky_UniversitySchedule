package com.epam.brest;

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
        return null;
    }

    @Override
    public Integer createUserService(User user) {
        return null;
    }

    @Override
    public Integer updateUserService(User user) {
        return null;
    }

    @Override
    public Integer deleteUserService(Integer userId) {
        return null;
    }

    @Override
    public Integer countUserService() {
        return null;
    }
}
