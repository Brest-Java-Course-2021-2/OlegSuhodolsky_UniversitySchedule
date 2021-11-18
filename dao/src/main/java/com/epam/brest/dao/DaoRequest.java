package com.epam.brest.dao;

import com.epam.brest.daoAPI.DaoRequestAPI;
import com.epam.brest.model.entity.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DaoRequest implements DaoRequestAPI {

    private final String GET_FROM_REQUEST_ALL =
            "select d.idR, d.id, d.groupe, d.pairs, d.subject from request d where id=:id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Logger logger = LogManager.getLogger(DaoRequest.class);

    @Autowired
    public DaoRequest(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Request> getAll(int id) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource().addValue("id", id);
       return namedParameterJdbcTemplate.query(GET_FROM_REQUEST_ALL,sqlParameterSource, new RequestRowMapper());
    }

    @Override
    public Request read(int id) {

        return null;
    }

    @Override
    public Integer write(Request request) {

        return null;
    }

    @Override
    public void update(Request request) {

    }

    @Override
    public void delete(Request request) {

    }

    /*MAPPER CLASS USER*/
    private class RequestRowMapper implements RowMapper<Request> {
        @Override
        public Request mapRow(ResultSet resultSet, int i) throws SQLException {
            Request request = new Request();
            request.setIdR(resultSet.getInt("idR"));
            request.setId(resultSet.getInt("id"));
            request.setGroupe(resultSet.getString("groupe"));
            request.setPairs(resultSet.getString("pairs"));
            request.setSubject(resultSet.getString("subject"));
            return request;
        }
    }


}
