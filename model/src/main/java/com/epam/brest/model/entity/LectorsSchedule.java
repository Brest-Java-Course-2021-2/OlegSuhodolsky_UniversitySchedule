package com.epam.brest.model.entity;

public class LectorsSchedule {

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
