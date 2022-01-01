package com.epam.brest.model.entity.dto;

import com.epam.brest.model.entity.Groupe;
import com.epam.brest.model.entity.User;

import java.util.ArrayList;

public class Schedule {


    int count = 0;
    int block = 5;
    ArrayList<ScheduleDay> scheduleObj = new ArrayList<>();
    ArrayList<Groupe> group;

    ArrayList<User> lector;
    ArrayList<String> groups;
    ArrayList<String[]> requests;
    ArrayList<String> lectors;
    private Object groupe;

    public Schedule() {
    }


    public void createLectorRequestsList(){}

    private class RequestsForGroupe {

        String groupe;
        String lector;
        String subject;
        Integer pairs;

        RequestsForGroupe() {
        }

        RequestsForGroupe(String lector, String groupe, String subject, Integer pairs) {
            this.groupe = groupe;
            this.lector = lector;
            this.subject = subject;
            this.pairs = pairs;
        }

        @Override
        public String toString() {
            return (this.lector + " " + this.groupe + " " + this.subject + " " + this.pairs);
        }


    }

    private class ScheduleDay {

        private Integer day;
        private String lectorName;
        private String groupeName;
        private String subject;
        private Integer numberPair;

        public ScheduleDay() {
        }

        public ScheduleDay(Integer day, String lectorName, String groupeName, String subject, Integer numberPair) {
            this.day = day;
            this.lectorName = lectorName;
            this.groupeName = groupeName;
            this.subject = subject;
            this.numberPair = numberPair;
        }

        public Integer getDay() {
            return day;
        }

        public String getLectorName() {
            return lectorName;
        }

        public String getGroupeName() {
            return groupeName;
        }

        public String getSubject() {
            return subject;
        }

        public Integer getNumberPair() {
            return numberPair;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public void setLectorName(String lectorName) {
            this.lectorName = lectorName;
        }

        public void setGroupeName(String groupeName) {
            this.groupeName = groupeName;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setNumberPair(Integer numberPair) {
            this.numberPair = numberPair;
        }

        @Override
        public String toString() {
            return "ScheduleDay{" + "day=" + day + ", lectorName=" + lectorName + ", groupeName=" + groupeName + ", subject=" + subject + ", numberPair=" + numberPair + '}';
        }

    }
}
