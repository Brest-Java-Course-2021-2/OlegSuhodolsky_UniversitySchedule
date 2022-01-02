package com.epam.brest.dao;

import com.epam.brest.dao.schedulemodel.RequestsForGroupe;
import com.epam.brest.daoAPI.DaoDtoSchedule;
import com.epam.brest.daoAPI.DaoUserAPI;
import com.epam.brest.model.entity.DaySchedule;
import com.epam.brest.model.entity.Groupe;
import com.epam.brest.model.entity.Request;
import com.epam.brest.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoSchedule implements DaoDtoSchedule {
    private DaoGroupe daoGroupe;
    private DaoUser daoUser;
    private DaoRequest daoRequest;

    @Autowired
    public DaoSchedule(DaoGroupe daoGroupe, DaoUser daoUser, DaoRequest daoRequest) {
        this.daoGroupe = daoGroupe;
        this.daoUser = daoUser;
        this.daoRequest = daoRequest;
    }

    @Override
    public boolean createScheduleDto() {

        List<Groupe> listGroupe = daoGroupe.getGroupesByName();
        String [] groupes = new String[listGroupe.size()];
        for (int i = 0; i < listGroupe.size() -1; i++){
            groupes[i] = listGroupe.get(i).getNameGroupe();
        }

        List<User> listUser = daoUser.getAllUsers();
        String [] users = new String[listUser.size()];
        for (int i = 0; i < listUser.size() -1; i++){
            users[i] = listUser.get(i).getName();
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
