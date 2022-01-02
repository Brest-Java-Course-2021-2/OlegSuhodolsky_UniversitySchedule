package com.epam.brest.dao.schedulemodel;

import com.epam.brest.model.entity.Groupe;
import com.epam.brest.model.entity.User;

import java.util.ArrayList;
import java.util.List;

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




    public void createLectorRequestsList(String [] groupes, String [] lectors, List <RequestsForGroupe> requestsForGroupes){

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
