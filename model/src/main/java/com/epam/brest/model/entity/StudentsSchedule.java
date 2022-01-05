package com.epam.brest.model.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentsSchedule {

    private String groupe;
    private List <List<String>> week;
    private List <String> pairByWeek;

    public StudentsSchedule(String groupe) {
        this.groupe = groupe;
        this.week = new ArrayList<>();
        this.pairByWeek = new ArrayList<>();
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public List<List<String>> getWeek() {
        return week;
    }

    public void setWeek(List<List<String>> week) {
        this.week = week;
    }

    public List<String> getPairByWeek() {
        return pairByWeek;
    }

    public void setPairByWeek(List<String> pairByWeek) {
        this.pairByWeek = pairByWeek;
    }

}
