package com.epam.brest.dao;

import com.epam.brest.daoAPI.DaoUserAPI;
import com.epam.brest.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoUser implements DaoUserAPI {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DaoUser(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> detAll() {
        String SQL = "SELECT *FROM user";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(User.class));

    }

    @Override
    public void read(int id) {

    }

    @Override
    public void write(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
