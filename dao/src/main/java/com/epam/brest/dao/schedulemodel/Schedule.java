package com.epam.brest.dao.schedulemodel;

import com.epam.brest.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Schedule {

    int count = 0;
    int block = 5;
    ArrayList<DaySchedule> scheduleObj = new ArrayList<>();


    public Schedule() {}

// Create schedule for lectors
    public List <LectorsSchedule> createScheduleForLectors (List<String> lectors,  List<DaySchedule> scheduleList){
        List <LectorsSchedule> lectorsSchedules = new ArrayList<>();

        for(String lector : lectors){
            for (int para = 1; para < 6; para++ ){
                LectorsSchedule lectorsSchedule = new LectorsSchedule();
                lectorsSchedule.setLector(lector);
                lectorsSchedule.setPair(para);
                for  (int dayWeek = 1; dayWeek < 6; dayWeek++ ){

                    for (DaySchedule daySchedule : scheduleList){
                        if ((lector == daySchedule.getLectorName()) && (para == daySchedule.getNumberPair())
                                && (dayWeek == daySchedule.getDay())){
                            if (dayWeek == 1){
                                lectorsSchedule.setMonday(daySchedule.getSubject());
                            } else{
                                if (dayWeek == 2){
                                    lectorsSchedule.setTuesday(daySchedule.getSubject());
                                } else {
                                    if (dayWeek == 3){
                                        lectorsSchedule.setWednesday(daySchedule.getSubject());
                                    } else {
                                        if (dayWeek == 4){
                                            lectorsSchedule.setThursday(daySchedule.getSubject());
                                        } else {
                                            if (dayWeek == 5){
                                                lectorsSchedule.setFriday(daySchedule.getSubject());
                                            }
                                        }
                                    }
                                }
                            }


                        }
                    }
                }
                lectorsSchedules.add(lectorsSchedule);
            }
        }

        return lectorsSchedules;
    }

 // Create schedule for students
    public List <StudentsSchedule> createScheduleForGroupe (List<String> groups,  List<DaySchedule> scheduleList){
           List <StudentsSchedule> studentsSchedules = new ArrayList<>();

           for(String gruppa : groups){
               for (int para = 1; para < 6; para++ ){
                   StudentsSchedule studentsSchedule = new StudentsSchedule();
                   studentsSchedule.setGroupe(gruppa);
                   studentsSchedule.setPair(para);
                   for  (int dayWeek = 1; dayWeek < 6; dayWeek++ ){

                       for (DaySchedule daySchedule : scheduleList){
                           if ((gruppa == daySchedule.getGroupeName()) && (para == daySchedule.getNumberPair())
                               && (dayWeek == daySchedule.getDay())){
                                if (dayWeek == 1){
                                    studentsSchedule.setMonday(daySchedule.getSubject());
                                } else{
                                    if (dayWeek == 2){
                                        studentsSchedule.setTuesday(daySchedule.getSubject());
                                    } else {
                                        if (dayWeek == 3){
                                            studentsSchedule.setWednesday(daySchedule.getSubject());
                                        } else {
                                            if (dayWeek == 4){
                                                studentsSchedule.setThursday(daySchedule.getSubject());
                                            } else {
                                                if (dayWeek == 5){
                                                    studentsSchedule.setFriday(daySchedule.getSubject());
                                                }
                                            }
                                        }
                                    }
                                }


                           }
                       }
                   }
                   studentsSchedules.add(studentsSchedule);
               }
           }

        return studentsSchedules;
    }



// Create schedule from requests
    public ArrayList<DaySchedule> createLectorRequestsList(
                                                List <String> groups
                                              , List <String> lectors
                                              , List <RequestsForGroupe> requestsForGroupes ) {

        RequestsForGroupe rbg;
        List<RequestsForGroupe> reqArr = requestsForGroupes;
        List<ArrayList<RequestsForGroupe>> scheduleArr = new ArrayList<>();
        boolean consist;

        for (int i = 0; i < groups.size(); i++) {
            String gr = groups.get(i);
            ArrayList<RequestsForGroupe> rbgArr = new ArrayList<>();
            for (int j = 0; j < lectors.size(); j++) {
                String lc = lectors.get(j);
                consist = false;
                for (int c = 0; c < reqArr.size(); c++) {
                    rbg = reqArr.get(c);
                    if (rbg.getGroupe().equals(gr) && rbg.getLector() == lc) {
                        rbgArr.add(rbg);
                        consist = true;
                    }
                }
                if (!consist) {
                    rbg = new RequestsForGroupe();
                    rbg.setLector(lc);
                    rbg.setGroupe(gr);
                    rbg.setSubject("");
                    rbg.setPairs(0);
                    rbgArr.add(rbg);
                }
            }
            scheduleArr.add(rbgArr);
        }



        while (block > 2) {
            ArrayList<ArrayList<RequestsForGroupe>> listArr = new ArrayList<>();
            for (int i = 0; i < scheduleArr.size(); i++) {
                ArrayList<RequestsForGroupe> rg = scheduleArr.get(i);
                if (calcPairsInDay(rg) == block) {
                    listArr.add(rg);
                    if (listArr.size() == block) {
                        createScheduleObjects(listArr);
                        listArr.clear();
                    }
                }
            }
            if (listArr.size() > 0) {
                         createScheduleObjects(listArr);
            }

        block--;
        }

    return scheduleObj;
    }


    private int calcPairsInDay(ArrayList<RequestsForGroupe> rbgArr) {
        int numPairs = 0;
        for (int i = 0; i < rbgArr.size(); i++) {
            numPairs += rbgArr.get(i).getPairs();
        }
        return (numPairs / 6 + 1);
    }

    private void createScheduleObjects(ArrayList<ArrayList<RequestsForGroupe>> listArr) {
        int[][] inDay = {
                {1, 3, 5, 2, 4, 6},
                {2, 4, 6, 1, 3, 5},
                {6, 2, 5, 4, 1, 3},
                {4, 5, 6, 1, 2, 3},
                {5, 6, 1, 2, 3, 4},
                {6, 1, 2, 3, 4, 5}
        };

        for (int i = 0; i < 6; i++) {
            listArr = fillDaySchedule(listArr, inDay[count][i]);
        }
        count++;
    }

    private ArrayList<ArrayList<RequestsForGroupe>> fillDaySchedule(ArrayList<ArrayList<RequestsForGroupe>> listArr, int day) {
        ArrayList<RequestsForGroupe> gArr;
        RequestsForGroupe rg = null;
        Integer pair = 0;
        int block1 = 4;
        for (int i = 0; i < listArr.size(); i++) {
            getNoNullElement(listArr);
            if (listArr.get(i) != null) {
                gArr = listArr.get(i);
            } else {
                gArr = null;
            }

            for (int j = 0; j < block1; j++) {
                if (i + j + 1 <= block1) {
                    pair = i + j + 1;
                } else {
                    if (i + j + 1 == block1 + 1) {
                        pair = 1;
                    } else {
                        pair++;
                    }
                }
                if (gArr != null) {
                    if (!gArr.isEmpty()) {
                        if (j < gArr.size()) {
                            rg = gArr.get(j);
                        } else {
                            rg = null;
                        }
                    }
                    if (rg != null) {
                        if (rg.getPairs() > 0) {
                            rg.setPairs(rg.getPairs()-1);
                            DaySchedule ds = new DaySchedule(rg.getLector(), rg.getGroupe(),rg.getPairs(), rg.getSubject(), day);
                            scheduleObj.add(ds);
                            System.out.println(ds);
                            gArr.set(j, rg);

                        }
                    }
                }
            }
            if (gArr.isEmpty()) {
                listArr.set(i, null);
            } else {
                listArr.set(i, gArr);
            }
        }
        return listArr;
    }

    private ArrayList<ArrayList<RequestsForGroupe>> getNoNullElement(ArrayList<ArrayList<RequestsForGroupe>> listArr) {
        int isZero = 0;

        for (int i = 0; i < listArr.size(); i++) {
            ArrayList<RequestsForGroupe> gArr = listArr.get(i);
            if (gArr.get(0).getPairs() == 0) {
                isZero++;
            }
        }
        if (isZero == listArr.size()) {
            for (int i = 0; i < listArr.size(); i++) {
                ArrayList<RequestsForGroupe> gArr = listArr.get(i);
                gArr.remove(0);
                listArr.set(i, gArr);
            }
            if (!listArr.isEmpty()) {
                getNoNullElement(listArr);
            }
        }
        return listArr;
    }

    public List<String> getUserNameList( List<User> listUser){
        List<String> users = new ArrayList<>();
        for (int i = 0; i < listUser.size() -1; i++){
            users.add(listUser.get(i).getName());
        }
        return users;
    }

    public List <String> getGroupeNameList( List<Groupe> listGroupe){
        List<String>  groupes = new ArrayList<>();
        for (int i = 0; i < listGroupe.size() -1; i++){
            groupes.add(listGroupe.get(i).getNameGroupe());
        }
        return groupes;
    }
}
