package com.epam.brest.dao;

import com.epam.brest.dao.schedulemodel.Schedule;
import com.epam.brest.daoAPI.DaoDtoSchedule;
import com.epam.brest.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoSchedule implements DaoDtoSchedule {
    private DaoGroupe daoGroupe;
    private DaoUser daoUser;
    private DaoRequest daoRequest;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Schedule schedule;

    @Autowired
    public DaoSchedule(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DaoGroupe daoGroupe, DaoUser daoUser, DaoRequest daoRequest, Schedule schedule) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.daoGroupe = daoGroupe;
        this.daoUser = daoUser;
        this.daoRequest = daoRequest;
        this.schedule = schedule;
    }

    @Override
    public boolean createScheduleDto() {

        List<Groupe> listGroupe = daoGroupe.getGroupesByName();
        List<String>  groupes = new ArrayList<>();
        for (int i = 0; i < listGroupe.size() -1; i++){
            groupes.add(listGroupe.get(i).getNameGroupe());
        }

        List<User> listUser = daoUser.getAllUsers();
        List<String> users = new ArrayList<>();
        for (int i = 0; i < listUser.size() -1; i++){
            users.add(listUser.get(i).getName());
        }

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


        return false;
    }

    @Override
    public List<DaySchedule> getScheduleForAll() {
        return null;
    }

    @Override
    public List<DaySchedule> getScheduleForTeacherDto() {
        return null;
    }

    @Override
    public List<DaySchedule> getScheduleForGroupeDto() {
        return null;
    }
}
