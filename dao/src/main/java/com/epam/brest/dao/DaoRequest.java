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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DaoRequest implements DaoRequestAPI {

    private final String GET_FROM_REQUEST_ALL =
            "select d.idR, d.id, d.groupe, d.pairs, d.subject from request d where id=:id order by d.idR";

    private final String INSERT_NEW_REQUEST =
            "insert into request (id,groupe,pairs,subject) values(:id, :groupe, :pairs, :subject)";

    private final String GET_FROM_REQUEST_BY_ID_AND_IDR = "select d.idR, d.id, d.groupe, d.pairs, d.subject from request d where id=:id and idR=:idR";

    private final String DELETE_REQUEST ="delete from request where id=:id and idR=:idR";



    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Logger logger = LogManager.getLogger(DaoRequest.class);

    @Autowired
    public DaoRequest(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Request> getAll(int id) {
        logger.info("GET REQUEST BY USER ID {}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.query(GET_FROM_REQUEST_ALL, sqlParameterSource, new RequestRowMapper());
    }

    @Override
    public Request read(int id, int idR) {
        logger.info("GET REQUEST BY USER ID AND IDR {}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("idR", idR);
        Request request = null;
        request = namedParameterJdbcTemplate.query(GET_FROM_REQUEST_BY_ID_AND_IDR,
                sqlParameterSource, new RequestRowMapper()).get(0);
        return request;
    }

    @Override
    public Integer write(Request request) {
        logger.info("NEW REQUEST BY USERS ID {}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("id", request.getId())
                        .addValue("groupe", request.getGroupe())
                        .addValue("pairs", request.getPairs())
                        .addValue("subject", request.getSubject());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_NEW_REQUEST, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void update(Request request) {

    }

    @Override
    public void delete(int id, int idR) {
        logger.info("DELETE REQUEST BY USER ID AND IDR {}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("idR", idR);

         namedParameterJdbcTemplate.update(DELETE_REQUEST, sqlParameterSource);

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
