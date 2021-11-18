package com.epam.brest.dao;

import com.epam.brest.daoAPI.DaoUserAPI;
import com.epam.brest.model.entity.User;
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
public class DaoUser implements DaoUserAPI {

    private final String GET_FROM_USER_ALL = "select d.id, d.name, d.login, d.password, d.email from user d order by d.name";
    private final String GET_FROM_USER_BY_ID = "select d.id, d.name, d.login, d.password, d.email from user d where id=:id";
    private final String SQL_CREATE_USER = "insert into user(name,login,password,email) values(:name, :login, :password, :email)";
    private final String SQL_UPDATE_USER = "update user set name=:name, login=:login, password=:password, email=:email where id=:id";
    private final String SQL_DELETE_USER_BY_ID = "delete from user where id=:id";


    private final Logger logger = LogManager.getLogger(DaoUser.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DaoUser(DataSource dataSource) {
        //  this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAll() {
        return namedParameterJdbcTemplate.query(GET_FROM_USER_ALL, new UserRowMapper());
    }

    @Override
    public List<User> read(int id) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.query(GET_FROM_USER_BY_ID, sqlParameterSource, new UserRowMapper());
    }

    @Override
    public Integer write(User user) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource();

        ((MapSqlParameterSource) sqlParameterSource).addValue("name", user.getName());
        ((MapSqlParameterSource) sqlParameterSource).addValue("login", user.getLogin());
        ((MapSqlParameterSource) sqlParameterSource).addValue("password", user.getPassword());
        ((MapSqlParameterSource) sqlParameterSource).addValue("email", user.getEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_USER, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public Integer updateUser(User user) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("id", user.getId());
        ((MapSqlParameterSource) sqlParameterSource).addValue("name", user.getName());
        ((MapSqlParameterSource) sqlParameterSource).addValue("login", user.getLogin());
        ((MapSqlParameterSource) sqlParameterSource).addValue("password", user.getPassword());
        ((MapSqlParameterSource) sqlParameterSource).addValue("email", user.getEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_UPDATE_USER, sqlParameterSource, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void delete(int id) {
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(SQL_DELETE_USER_BY_ID, sqlParameterSource);
    }


    /*MAPPER CLASS USER*/
    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            return user;
        }
    }

}