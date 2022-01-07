package com.epam.brest.model.entity;

public class LectorsSchedule {
    private int idLS;
    private String lector;
    private int pair;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    public LectorsSchedule() {
        this.monday = "";
        this.tuesday = "";
        this.wednesday = "";
        this.thursday = "";
        this.friday = "";
    }

    public LectorsSchedule(  int idLS
                           , String lector
                           , int pair
                           , String monday
                           , String tuesday
                           , String wednesday
                           , String thursday
                           , String friday) {
        this.idLS = idLS;
        this.lector = lector;
        this.pair = pair;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public int getIdLS() {
        return idLS;
    }

    public void setIdLS(int idLS) {
        this.idLS = idLS;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }
}
