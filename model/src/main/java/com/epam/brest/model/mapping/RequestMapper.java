package com.epam.brest.model.mapping;

import com.epam.brest.model.entity.Request;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RequestMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Request request = new Request();
        request.setIDR(resultSet.getInt("IDR"));
        request.setID(resultSet.getInt("ID"));
        request.setGroupe(resultSet.getString("groupe"));
        request.setPairs(resultSet.getString("pairs"));
        request.setSubject(resultSet.getString("password"));
        return request;
    }
}
