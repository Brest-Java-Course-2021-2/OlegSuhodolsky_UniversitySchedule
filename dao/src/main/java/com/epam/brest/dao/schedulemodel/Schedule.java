package com.epam.brest.dao.schedulemodel;

import com.epam.brest.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Schedule {

    /*int count = 0;
    int block = 5;*/
    //List<DaySchedule> scheduleObj = new ArrayList<>();
    List <DaySchedule> scheduleObjects = new ArrayList<>();

    private int [][] daysForSchedule = {
                                        {1,3,5,2,4},
                                        {2,4,1,3,5},
                                        {5,2,3,4,1} };

    private int [][] pairsForSchedule = {
                                        {1,2,3,4},
                                        {2,3,4,1},
                                        {3,4,1,2},
                                        {4,1,2,3}};



    public Schedule() {
    }


 /*
 create Schedule
 */

    public List<DaySchedule> createSchedule(
              List<String> groups
            , List<String> lectors
            , List<RequestsForGroupe> requestsForGroupes) {

        scheduleObjects = new ArrayList<>();
        List <List<RequestsForGroupe>> listOfSortedGroupesRequests = new ArrayList<>();
        List <List<RequestsForGroupe>> blockOfSortedGroupesRequests = new ArrayList<>();
        boolean ifConsistInRequest = false;

        for (String groupe : groups){
            List <RequestsForGroupe> sortedRequests = new ArrayList<>();
            for (String lector : lectors){
                ifConsistInRequest = false;
                for (RequestsForGroupe requestsGroupe : requestsForGroupes){
                    if ((groupe == requestsGroupe.getGroupe()) && (lector == requestsGroupe.getLector())){
                        sortedRequests.add(requestsGroupe);
                        ifConsistInRequest = true;
                    }
                }
                    if(!ifConsistInRequest){
                        sortedRequests.add(new RequestsForGroupe( lector , groupe,"", 0 ));
                    }
                    ifConsistInRequest = false;

            }
            listOfSortedGroupesRequests.add(sortedRequests);
        }

        if (listOfSortedGroupesRequests.size() > 0){
            if (listOfSortedGroupesRequests.get(0).size() < 4){
                return scheduleObjects;
            }
        }
            int i = 0;
            for (List <RequestsForGroupe> forGroupeList : listOfSortedGroupesRequests){
                if (blockOfSortedGroupesRequests.size() < 4){
                    blockOfSortedGroupesRequests.add(forGroupeList);
                }
                if (blockOfSortedGroupesRequests.size() == 4){
                    createDaySchedule(blockOfSortedGroupesRequests, daysForSchedule[i], pairsForSchedule);
                    blockOfSortedGroupesRequests = new ArrayList<>();
                    i++;
                }
            }

            if (blockOfSortedGroupesRequests.size() > 0){
                createDaySchedule(blockOfSortedGroupesRequests, daysForSchedule[i], pairsForSchedule);
            }

            for (DaySchedule d : scheduleObjects){
                System.out.println("DaySchedule - > " + d);
            }



    return scheduleObjects;
    }

 // Create DaySchedule -> return DaySchedule list

    private void createDaySchedule (List<List<RequestsForGroupe>> blockOfSortedGroupesRequests, int [] days
                                  , int [][] pairsForSchedule) {

                  for (int day = 0; day < days.length; day++){
                      blockOfSortedGroupesRequests = normalizeGroupeRequests (blockOfSortedGroupesRequests);
                      if (blockOfSortedGroupesRequests != null) {
                          for (int i = 0; i < blockOfSortedGroupesRequests.size(); i++) {
                              List<RequestsForGroupe> requestsForGroupes = blockOfSortedGroupesRequests.get(i);
                              for (int pair = 0; pair < pairsForSchedule[i].length; pair++) {
                                  RequestsForGroupe requests = requestsForGroupes.get(pair);
                                  if (requests.getPairs() > 0) {
                                      scheduleObjects.add(new DaySchedule(requests.getLector()
                                              , requests.getGroupe()
                                              , pairsForSchedule[i][pair]
                                              , requests.getSubject()
                                              , days[day]));
                                      requests.setPairs(requests.getPairs() - 1);
                                      requestsForGroupes.set(pair, requests);
                                  }
                              }
                              blockOfSortedGroupesRequests.set(i, requestsForGroupes);
                          }
                      }
                  }
    }

//==================================================================================================================
    private List<List<RequestsForGroupe>> normalizeGroupeRequests (List<List<RequestsForGroupe>> blockOfSortedGroupesRequests){

      if (blockOfSortedGroupesRequests.get(0).size() > 4){
          blockOfSortedGroupesRequests = deleteNullColumns (blockOfSortedGroupesRequests);
      }
      if (blockOfSortedGroupesRequests.get(0).size() == 4){
          blockOfSortedGroupesRequests = ifNullColumns(blockOfSortedGroupesRequests);
      }



        return blockOfSortedGroupesRequests;
    }
//==================================================================================================================
    private List<List<RequestsForGroupe>> deleteNullColumns(List<List<RequestsForGroupe>> blockOfSortedGroupesRequests){
        int isZero = 0;
        for (List<RequestsForGroupe> requestsForGroupes : blockOfSortedGroupesRequests){
                       isZero += requestsForGroupes.get(0).getPairs();
                    }
        if (isZero == 0){
            for (int i = 0; i < blockOfSortedGroupesRequests.size(); i++){
                List<RequestsForGroupe> requestsForGroupes = blockOfSortedGroupesRequests.get(i);
                requestsForGroupes.remove(0);
                blockOfSortedGroupesRequests.set(i, requestsForGroupes);
            }
        }

        return blockOfSortedGroupesRequests;
    }
//===================================================================================================================
    private List<List<RequestsForGroupe>> ifNullColumns(List<List<RequestsForGroupe>> blockOfSortedGroupesRequests){
        int countZero = 0;
        for (List <RequestsForGroupe> requestsForGroupes : blockOfSortedGroupesRequests){
            for (RequestsForGroupe requests : requestsForGroupes){
                countZero += requests.getPairs();
            }
        }
        if (countZero > 0){
            return blockOfSortedGroupesRequests;
        } else {
            return null;
        }
    }
//======================================================================================================================
/*
End create
*/
/*

    // Create schedule from requests
    public List<DaySchedule> createLectorRequestsList(
            List<String> groups
            , List<String> lectors
            , List<RequestsForGroupe> requestsForGroupes) {

        RequestsForGroupe rbg;
        List<RequestsForGroupe> reqArr = requestsForGroupes;
        List<List<RequestsForGroupe>> scheduleArr = new ArrayList<>();
        boolean consist;

        for (int i = 0; i < groups.size(); i++) {
            String gr = groups.get(i);
            List<RequestsForGroupe> rbgArr = new ArrayList<>();
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

        System.out.println("REQUESTS for Groupe sorted");
        for (List<RequestsForGroupe> rq : scheduleArr) {
            System.out.println();
            for (RequestsForGroupe r : rq) {
                System.out.print(r);
            }
        }


        while (block > 2) {
            List<List<RequestsForGroupe>> listArr = new ArrayList<>();
            for (int i = 0; i < scheduleArr.size(); i++) {
                List<RequestsForGroupe> rg = scheduleArr.get(i);
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

        for (DaySchedule d : scheduleObj) {
            System.out.println("SCHEDULE_DAY -> " + d);
        }

        return scheduleObj;
    }


    private int calcPairsInDay(List<RequestsForGroupe> rbgArr) {
        int numPairs = 0;
        for (int i = 0; i < rbgArr.size(); i++) {
            numPairs += rbgArr.get(i).getPairs();
        }
        return (numPairs / 6 + 1);
    }

    private void createScheduleObjects(List<List<RequestsForGroupe>> listArr) {
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

    private List<List<RequestsForGroupe>> fillDaySchedule(List<List<RequestsForGroupe>> listArr, int day) {
        List<RequestsForGroupe> gArr;
        RequestsForGroupe rg = null;
        Integer pair = 0;
        int block1 = 4;
        for (int i = 0; i < listArr.size(); i++) {
            listArr = getNoNullElement(listArr);
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
                            rg.setPairs(rg.getPairs() - 1);
                            DaySchedule ds = new DaySchedule(rg.getLector(), rg.getGroupe(), rg.getPairs(), rg.getSubject(), day);
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

    private List<List<RequestsForGroupe>> getNoNullElement(List<List<RequestsForGroupe>> listArr) {
        int isZero = 0;

        for (int i = 0; i < listArr.size(); i++) {
            List<RequestsForGroupe> gArr = listArr.get(i);
            if (gArr.get(0).getPairs() == 0) {
                isZero++;
            }
        }
        if (isZero == listArr.size()) {
            for (int i = 0; i < listArr.size(); i++) {
                List<RequestsForGroupe> gArr = listArr.get(i);
                gArr.remove(0);
                listArr.set(i, gArr);
            }
            if (!listArr.isEmpty()) {
                getNoNullElement(listArr);
            }
        }
        return listArr;
    }

*/
    public List<String> getUserNameList(List<User> listUser) {
        List<String> users = new ArrayList<>();
        for (User u : listUser) {
            users.add(u.getName());
        }
        return users;
    }

    public List<String> getGroupeNameList(List<Groupe> listGroupe) {
        List<String> groupes = new ArrayList<>();
        for (Groupe g : listGroupe) {
            groupes.add(g.getNameGroupe());
        }
        return groupes;
    }

    // Create schedule for lectors
    public List<LectorsSchedule> createScheduleForLectors(List<String> lectors, List<DaySchedule> scheduleList) {
        List<LectorsSchedule> lectorsSchedules = new ArrayList<>();

        for (String lector : lectors) {
            for (int para = 1; para < 6; para++) {
                LectorsSchedule lectorsSchedule = new LectorsSchedule();
                lectorsSchedule.setLector(lector);
                lectorsSchedule.setPair(para);
                for (int dayWeek = 1; dayWeek < 6; dayWeek++) {

                    for (DaySchedule daySchedule : scheduleList) {
                        if ((lector == daySchedule.getLectorName()) && (para == daySchedule.getNumberPair())
                                && (dayWeek == daySchedule.getDay())) {
                            if (dayWeek == 1) {
                                lectorsSchedule.setMonday(daySchedule.getSubject());
                            } else {
                                if (dayWeek == 2) {
                                    lectorsSchedule.setTuesday(daySchedule.getSubject());
                                } else {
                                    if (dayWeek == 3) {
                                        lectorsSchedule.setWednesday(daySchedule.getSubject());
                                    } else {
                                        if (dayWeek == 4) {
                                            lectorsSchedule.setThursday(daySchedule.getSubject());
                                        } else {
                                            if (dayWeek == 5) {
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
    public List<StudentsSchedule> createScheduleForGroupe(List<String> groups, List<DaySchedule> scheduleList) {
        List<StudentsSchedule> studentsSchedules = new ArrayList<>();
        //scheduleObj.clear();
        for (String gruppa : groups) {
            for (int para = 1; para < 6; para++) {
                StudentsSchedule studentsSchedule = new StudentsSchedule();
                studentsSchedule.setGroupe(gruppa);
                studentsSchedule.setPair(para);
                for (int dayWeek = 1; dayWeek < 6; dayWeek++) {

                    for (DaySchedule daySchedule : scheduleList) {
                        if ((gruppa == daySchedule.getGroupeName()) && (para == daySchedule.getNumberPair())
                                && (dayWeek == daySchedule.getDay())) {
                            if (dayWeek == 1) {
                                studentsSchedule.setMonday(daySchedule.getSubject());
                            } else {
                                if (dayWeek == 2) {
                                    studentsSchedule.setTuesday(daySchedule.getSubject());
                                } else {
                                    if (dayWeek == 3) {
                                        studentsSchedule.setWednesday(daySchedule.getSubject());
                                    } else {
                                        if (dayWeek == 4) {
                                            studentsSchedule.setThursday(daySchedule.getSubject());
                                        } else {
                                            if (dayWeek == 5) {
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

}
