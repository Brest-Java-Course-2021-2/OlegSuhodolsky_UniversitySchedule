package com.epam.brest.model.mapping;

import com.epam.brest.model.entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper {

    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt("id"));
        admin.setName(resultSet.getString("name"));
        admin.setLogin(resultSet.getString("login"));
        admin.setPassword(resultSet.getString("password"));
        admin.setEmail(resultSet.getString("email"));

        return admin;
    }

}

