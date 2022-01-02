package com.epam.brest.model.entity;

public class DaySchedule {

    private Integer idS;
    private Integer day;
    private String lectorName;
    private String groupeName;
    private String subject;
    private Integer numberPair;

    public DaySchedule() {}

    public DaySchedule(Integer day, String lectorName, String groupeName, String subject, Integer numberPair) {
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
