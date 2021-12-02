package com.epam.brest.dao;

import com.epam.brest.daoAPI.DaoRequestAPI;
import com.epam.brest.model.entity.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component
public class DaoRequest implements DaoRequestAPI {

    @Value("${GET_FROM_REQUEST_ALL}")
    private String getFromRequestAll;

    @Value("${INSERT_NEW_REQUEST}")
    private String insertNewRequest;

    @Value("${GET_FROM_REQUEST_BY_ID_AND_IDR}")
    private String getFromRequestByIdAndIdr;

    @Value("${DELETE_REQUEST}")
    private String deleteRequest;

    @Value("${UPDATE_REQUEST}")
    private String updateRequest;


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Logger logger = LogManager.getLogger(DaoRequest.class);


    @Autowired
    public DaoRequest(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Request> getAllRequests(int id) {
        logger.info("GET REQUEST BY USER ID {}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.query(getFromRequestAll, sqlParameterSource, new RequestRowMapper());
    }

    @Override
    public Request readRequest(int id, int idR) {
        logger.info("GET REQUEST BY USER ID AND IDR {}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("idR", idR);
        Request request = null;
        request = namedParameterJdbcTemplate.queryForObject(getFromRequestByIdAndIdr,
                sqlParameterSource, new RequestRowMapper());
        return request;
    }



    @Override
    public Integer writeRequest(Request request) {
        logger.info("NEW REQUEST BY USERS ID {}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("id", request.getId())
                        .addValue("groupe", request.getGroupe())
                        .addValue("pairs", request.getPairs())
                        .addValue("subject", request.getSubject())
                        .addValue("date", new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertNewRequest, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer updateRequest(Request request) {
        logger.info("UPDATE REQUEST  {request}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("idR", request.getIdR())
                        .addValue("id", request.getId())
                        .addValue("groupe", request.getGroupe())
                        .addValue("pairs", request.getPairs())
                        .addValue("subject", request.getSubject())
                        .addValue("date", request.getDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(updateRequest, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();

    }

    @Override
    public Integer deleteRequest(int id, int idR) {
        logger.info("DELETE REQUEST BY USER ID AND IDR {}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("idR", idR);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(deleteRequest, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();

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
            request.setDate(resultSet.getDate("date"));
            return request;
        }
    }


}
