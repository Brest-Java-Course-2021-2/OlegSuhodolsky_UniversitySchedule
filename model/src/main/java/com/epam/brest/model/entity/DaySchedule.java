package com.epam.brest.model.entity;

public class DaySchedule {

    private int idS;
    private int day;
    private String lectorName;
    private String groupeName;
    private String subject;
    private int numberPair;

    public DaySchedule() {}

    public DaySchedule(String lectorName, String groupeName, Integer numberPair, String subject, Integer day) {
        this.day = day;
        this.lectorName = lectorName;
        this.groupeName = groupeName;
        this.subject = subject;
        this.numberPair = numberPair;
    }

    public DaySchedule(int idS, String lectorName, String groupeName, Integer numberPair, String subject, Integer day) {
        this.idS = idS;
        this.day = day;
        this.lectorName = lectorName;
        this.groupeName = groupeName;
        this.subject = subject;
        this.numberPair = numberPair;
    }
    public Integer getIdS() {
        return idS;
    }

    public void setIdS(Integer idS) {
        this.idS = idS;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getLectorName() {
        return lectorName;
    }

    public void setLectorName(String lectorName) {
        this.lectorName = lectorName;
    }

    public String getGroupeName() {
        return groupeName;
    }

    public void setGroupeName(String groupeName) {
        this.groupeName = groupeName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getNumberPair() {
        return numberPair;
    }

    public void setNumberPair(Integer numberPair) {
        this.numberPair = numberPair;
    }

    @Override
    public String toString() {
        return "DaySchedule{" +
                "idS=" + idS +
                ", day=" + day +
                ", lectorName='" + lectorName + '\'' +
                ", groupeName='" + groupeName + '\'' +
                ", subject='" + subject + '\'' +
                ", numberPair=" + numberPair +
                '}';
    }
}
