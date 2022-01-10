package com.epam.brest.dao;

import com.epam.brest.daoAPI.DaoGroupeAPI;
import com.epam.brest.model.entity.Groupe;
import com.epam.brest.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DaoGroupe implements DaoGroupeAPI {

    @Value("${GET_FROM_GROUPE_NAMES}")
    private String getFromGroupeNames;

    private final Logger logger = LogManager.getLogger(DaoUser.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DaoGroupe(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Groupe> getGroupesByName() {
        logger.info("GET ALL NAME GROUPES {}");
        return namedParameterJdbcTemplate.query(getFromGroupeNames, new GroupeRowMapper());
    }




    /*MAPPER CLASS USER*/
    private class GroupeRowMapper implements RowMapper<Groupe> {
        @Override
        public Groupe mapRow(ResultSet resultSet, int i) throws SQLException {
            Groupe groupe = new Groupe();
           // groupe.setIdGroupe(resultSet.getInt("idGroupe"));
            groupe.setNameGroupe(resultSet.getString("nameGroupe"));
            return groupe;
        }
    }
}
