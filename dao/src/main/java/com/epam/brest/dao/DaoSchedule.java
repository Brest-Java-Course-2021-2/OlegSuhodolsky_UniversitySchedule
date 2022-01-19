package com.epam.brest.dao;

import com.epam.brest.dao.schedulemodel.Schedule;
import com.epam.brest.daoAPI.DaoDtoScheduleAPI;
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
public class DaoSchedule implements DaoDtoScheduleAPI {

    @Value("${INSERT_TO_SCHEDULE_ALL}")
    private String insertToScheduleAll;

    @Value("${GET_FROM_SCHEDULE_ALL}")
    private String getFromScheduleAll;

    @Value("${GET_FROM_SCHEDULE_GROUPE}")
    private String getFromScheduleGroupe;

    @Value("${DELETE_ALL_SCHEDULE}")
    private String deleteScheduleAll;

    @Value("${DELETE_SCHEDULE_STUDENTS}")
    private String deleteScheduleStudents;

    @Value("${DELETE_SCHEDULE_LECTORS}")
    private String deleteScheduleLectors;

    @Value("${INSERT_TO_SCHEDULE_STUDENTS}")
    private String insertToScheduleStudents;

    @Value("${INSERT_TO_SCHEDULE_LECTORS}")
    private String insertToScheduleLectors;

    @Value("${GET_FROM_SCHEDULE_STUDENTS}")
    private String getFromScheduleStudents;

    @Value("${GET_FROM_SCHEDULE_LECTORS}")
    private String getFromScheduleLectors;


    private DaoGroupe daoGroupe;
    private DaoUser daoUser;
    private DaoRequest daoRequest;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Schedule schedule;

    private final Logger logger = LogManager.getLogger(DaoSchedule.class);

    @Autowired
    public DaoSchedule(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DaoGroupe daoGroupe
                     , DaoUser daoUser, DaoRequest daoRequest, Schedule schedule) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.daoGroupe = daoGroupe;
        this.daoUser = daoUser;
        this.daoRequest = daoRequest;
        this.schedule = schedule;
    }

    //  Create schedule from requests
    @Override
    public int createScheduleDto() {
        logger.debug("Execute test: create schedule() - > TRUE");
        SqlParameterSource sqlParameterSourceDelete =
                new MapSqlParameterSource().addValue("id", null);

        namedParameterJdbcTemplate.update(deleteScheduleAll, sqlParameterSourceDelete);
        namedParameterJdbcTemplate.update(deleteScheduleStudents, sqlParameterSourceDelete);
        namedParameterJdbcTemplate.update(deleteScheduleLectors, sqlParameterSourceDelete);

        List<Groupe> listGroupe = daoGroupe.getGroupesByName();
        List<String> groupes = schedule.getGroupeNameList(listGroupe);

        List<User> listUser = daoUser.getAllUsers();
        List<String> users = schedule.getUserNameList(listUser);


        List<RequestsForGroupe> requestsForGroupes = new ArrayList<>();
        for (User u : listUser) {
            List<Request> listRequest = daoRequest.getAllRequests(u.getId());
            for (Request r : listRequest) {
                requestsForGroupes.add(new RequestsForGroupe(
                        u.getName()
                        , r.getGroupe()
                        , r.getSubject()
                        , Integer.parseInt(r.getPairs())));
            }
        }
        List<DaySchedule> scheduleList = schedule.createSchedule(groupes, users, requestsForGroupes);
        logger.info("INSERT NEW SCHEDULE {}");
        int count = 0;
        for (DaySchedule daySchedule : scheduleList) {
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

        List<StudentsSchedule> studentsSchedules = schedule.createScheduleForGroupe(groupes, scheduleList);
        logger.info("INSERT NEW SCHEDULE FOR STUDENTS{}");
        for (StudentsSchedule studentsSchedule : studentsSchedules) {
            SqlParameterSource sqlParameterSource =
                    new MapSqlParameterSource();
            ((MapSqlParameterSource) sqlParameterSource).addValue("groupe", studentsSchedule.getGroupe());
            ((MapSqlParameterSource) sqlParameterSource).addValue("pair", studentsSchedule.getPair());
            ((MapSqlParameterSource) sqlParameterSource).addValue("monday", studentsSchedule.getMonday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("tuesday", studentsSchedule.getTuesday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("wednesday", studentsSchedule.getWednesday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("thursday", studentsSchedule.getThursday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("friday", studentsSchedule.getFriday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("saturday", studentsSchedule.getSaturday());

            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insertToScheduleStudents, sqlParameterSource, keyHolder);
        }

        List<LectorsSchedule> lectorsSchedules = schedule.createScheduleForLectors(users, scheduleList);
        logger.info("INSERT NEW SCHEDULE FOR STUDENTS{}");
        for (LectorsSchedule lectorsSchedule : lectorsSchedules) {
            SqlParameterSource sqlParameterSource =
                    new MapSqlParameterSource();
            ((MapSqlParameterSource) sqlParameterSource).addValue("lector", lectorsSchedule.getLector());
            ((MapSqlParameterSource) sqlParameterSource).addValue("pair", lectorsSchedule.getPair());
            ((MapSqlParameterSource) sqlParameterSource).addValue("monday", lectorsSchedule.getMonday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("tuesday", lectorsSchedule.getTuesday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("wednesday", lectorsSchedule.getWednesday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("thursday", lectorsSchedule.getThursday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("friday", lectorsSchedule.getFriday());
            ((MapSqlParameterSource) sqlParameterSource).addValue("saturday", lectorsSchedule.getSaturday());

            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insertToScheduleLectors, sqlParameterSource, keyHolder);
        }
        return count;
    }


    // Get schedule for students
    @Override
    public List<List<StudentsSchedule>> getScheduleForAllStudents() {
        logger.info("READ SCHEDULE FOR ALL GROUPES {}");
        List<Groupe> listGroupe = daoGroupe.getGroupesByName();
        List<String> groupes = schedule.getGroupeNameList(listGroupe);
        List<List<StudentsSchedule>> studentsSchedulesAllGroupes = new ArrayList<>();
        //List<StudentsSchedule> studentsSchedules = null;
        for (String groupe : groupes) {
            SqlParameterSource sqlParameterSource =
                    new MapSqlParameterSource().addValue("groupe", groupe);
            List<StudentsSchedule> studentsSchedules = new ArrayList<>();
            studentsSchedules = (List<StudentsSchedule>) namedParameterJdbcTemplate.query(
                    getFromScheduleStudents, sqlParameterSource
                    , new DaoSchedule.StudentsScheduleRowMapper());
            studentsSchedulesAllGroupes.add(studentsSchedules);

        }

        return studentsSchedulesAllGroupes;
    }

    // Create schedule for teacher
    @Override
    public List<LectorsSchedule> getScheduleForTeacherDto(String lector) {
        logger.info("READ SCHEDULE FOR  LECTOR {lectorName}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource().addValue("lector", lector);
        List<LectorsSchedule> lectorsSchedules = null;
        lectorsSchedules = (List<LectorsSchedule>) namedParameterJdbcTemplate.query(
                getFromScheduleLectors, sqlParameterSource
                , new DaoSchedule.LectorsScheduleRowMapper());
        return lectorsSchedules;
    }

    // Create schedule for one groupe
    @Override
    public List<StudentsSchedule> getScheduleForGroupeDto(String groupe) {
        logger.info("READ SCHEDULE FOR  GROUPE {groupeName}");
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource().addValue("groupe", groupe);
        List<StudentsSchedule> studentsSchedules = null;
        studentsSchedules = (List<StudentsSchedule>) namedParameterJdbcTemplate.query(
                getFromScheduleStudents, sqlParameterSource
                , new DaoSchedule.StudentsScheduleRowMapper());
        return studentsSchedules;
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

    /*MAPPER CLASS StudentsSchedule*/
    private class StudentsScheduleRowMapper implements RowMapper<StudentsSchedule> {
        @Override
        public StudentsSchedule mapRow(ResultSet resultSet, int i) throws SQLException {
            StudentsSchedule studentsSchedule = new StudentsSchedule();
            studentsSchedule.setIdSS(resultSet.getInt("idSS"));
            studentsSchedule.setGroupe(resultSet.getString("groupe"));
            studentsSchedule.setPair(resultSet.getInt("pair"));
            studentsSchedule.setMonday(resultSet.getString("monday"));
            studentsSchedule.setTuesday(resultSet.getString("tuesday"));
            studentsSchedule.setWednesday(resultSet.getString("wednesday"));
            studentsSchedule.setThursday(resultSet.getString("thursday"));
            studentsSchedule.setFriday(resultSet.getString("friday"));
            studentsSchedule.setSaturday(resultSet.getString("saturday"));
            return studentsSchedule;
        }
    }

    /*MAPPER CLASS LectorsSchedule*/
    private class LectorsScheduleRowMapper implements RowMapper<LectorsSchedule> {
        @Override
        public LectorsSchedule mapRow(ResultSet resultSet, int i) throws SQLException {
            LectorsSchedule lectorsSchedule = new LectorsSchedule();
            lectorsSchedule.setIdLS(resultSet.getInt("idLS"));
            lectorsSchedule.setLector(resultSet.getString("lector"));
            lectorsSchedule.setPair(resultSet.getInt("pair"));
            lectorsSchedule.setMonday(resultSet.getString("monday"));
            lectorsSchedule.setTuesday(resultSet.getString("tuesday"));
            lectorsSchedule.setWednesday(resultSet.getString("wednesday"));
            lectorsSchedule.setThursday(resultSet.getString("thursday"));
            lectorsSchedule.setFriday(resultSet.getString("friday"));
            lectorsSchedule.setSaturday(resultSet.getString("saturday"));
            return lectorsSchedule;
        }
    }


}
