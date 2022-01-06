package com.epam.brest.dao;

import com.epam.brest.dao.schedulemodel.Schedule;
import com.epam.brest.daoAPI.DaoDtoSchedule;
import com.epam.brest.model.entity.*;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class DaoSchedule implements DaoDtoSchedule {

    @Value("${INSERT_TO_SCHEDULE_ALL}")
    private String insertToScheduleAll;

    @Value("${GET_FROM_SCHEDULE_ALL}")
    private String getFromScheduleAll;

    @Value("${GET_FROM_SCHEDULE_GROUPE}")
    private String getFromScheduleGroupe;


    @Value("${DELETE_ALL_SCHEDULE}")
    private String deleteScheduleAll;


    private DaoGroupe daoGroupe;
    private DaoUser daoUser;
    private DaoRequest daoRequest;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Schedule schedule;

    private final Logger logger = LogManager.getLogger(DaoSchedule.class);

    @Autowired
    public DaoSchedule(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DaoGroupe daoGroupe, DaoUser daoUser, DaoRequest daoRequest, Schedule schedule) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.daoGroupe = daoGroupe;
        this.daoUser = daoUser;
        this.daoRequest = daoRequest;
        this.schedule = schedule;
    }

//  Create schedule from requests
    @Override
    public Integer createScheduleDto() {
        logger.debug("Execute test: create schedule() - > TRUE");
        SqlParameterSource sqlParameterSourceDelete =
                new MapSqlParameterSource().addValue("id", 0);

        namedParameterJdbcTemplate.update(deleteScheduleAll, sqlParameterSourceDelete);

        List<Groupe> listGroupe = daoGroupe.getGroupesByName();
        List<String>  groupes = schedule.getGroupeNameList(listGroupe);

        List<User> listUser = daoUser.getAllUsers();
        List<String> users = schedule.getUserNameList(listUser);


        List<RequestsForGroupe> requestsForGroupes = new ArrayList<>();
        for (int i = 0; i < listUser.size() -1; i++){
            User user = listUser.get(i);
            List<Request> listRequest = daoRequest.getAllRequests(user.getId());
            for (int j = 0; j < listRequest.size()-1; j++){
                Request request = listRequest.get(j);
                requestsForGroupes.add(new RequestsForGroupe(
                        user.getName()
                        , request.getGroupe()
                        , request.getSubject()
                        , Integer.parseInt(request.getPairs())));
             }
        }



        List<DaySchedule> scheduleList = schedule.createLectorRequestsList(groupes, users, requestsForGroupes);

        logger.info("INSERT NEW SCHEDULE {}");
        Integer count = 0;
        for (DaySchedule daySchedule : scheduleList){
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource();

        ((MapSqlParameterSource) sqlParameterSource).addValue("lectorName", daySchedule.getLectorName());
        ((MapSqlParameterSource) sqlParameterSource).addValue("groupeName", daySchedule.getGroupeName());
        ((MapSqlParameterSource) sqlParameterSource).addValue("numberPair", daySchedule.getNumberPair());
        ((MapSqlParameterSource) sqlParameterSource).addValue("subject", daySchedule.getSubject());
        ((MapSqlParameterSource) sqlParameterSource).addValue("day", daySchedule.getDay());


        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertToScheduleAll, sqlParameterSource, keyHolder);
        count++;
        }

        List <StudentsSchedule> studentsSchedules = schedule.createScheduleForGroupe (groupes, scheduleList);
        List <LectorsSchedule> lectorsSchedules = schedule.createScheduleForLectors (users, scheduleList);

        return count;
    }

// Create schedule for students
    @Override
    public List<StudentsSchedule> getScheduleForAll() {
        logger.info("READ SCHEDULE FOR ALL GROUPES {}");
       return null;
    }

// Create schedule for teacher
    @Override
    public List<DaySchedule> getScheduleForTeacherDto() {
        return null;
    }

// Create schedule for one groupe
    @Override
    public List<DaySchedule> getScheduleForGroupeDto() {
        return null;
    }


    /*MAPPER CLASS DaySchedule*/
    private class DayScheduleRowMapper implements RowMapper<DaySchedule> {
        @Override
        public DaySchedule mapRow(ResultSet resultSet, int i) throws SQLException {
            DaySchedule daySchedule = new DaySchedule();
            daySchedule.setIdS(resultSet.getInt("idS"));
            daySchedule.setLectorName(resultSet.getString("lectorName"));
            daySchedule.setGroupeName(resultSet.getString("groupeName"));
            daySchedule.setNumberPair(resultSet.getInt("numberPair"));
            daySchedule.setSubject(resultSet.getString("subject"));
            daySchedule.setDay(resultSet.getInt("day"));
            return daySchedule;
        }
    }
}
