package com.epam.brest.dao;

import com.epam.brest.daoAPI.DaoRequestAPI;
import com.epam.brest.model.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DaoRequest implements DaoRequestAPI {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DaoRequest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Request> detAll() {
        return null;
    }

    @Override
    public void read(int id) {

    }

    @Override
    public void write(Request request) {

    }

    @Override
    public void update(Request request) {

    }

    @Override
    public void delete(Request request) {

    }
}
