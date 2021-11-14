package com.epam.brest.dao;

import com.epam.brest.daoAPI.DaoUserAPI;
import com.epam.brest.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DaoUser implements DaoUserAPI {

    //private final JdbcTemplate jdbcTemplate;
    private final Logger logger = LogManager.getLogger(DaoUser.class);
    //private final DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public DaoUser(DataSource dataSource) {
      //  this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<User> getAll() {

        String SQL = "select d.id, d.name, d.login, d.password, d.email from user d order by d.name";
        String sql = "select *from user";
        logger.debug("Start: getAll()");
        return namedParameterJdbcTemplate.query(sql,new UserRowMapper());

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
