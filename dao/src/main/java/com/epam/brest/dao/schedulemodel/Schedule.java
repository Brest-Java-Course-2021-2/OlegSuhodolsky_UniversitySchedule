package com.epam.brest.dao.schedulemodel;

import com.epam.brest.model.entity.DaySchedule;
import com.epam.brest.model.entity.Groupe;
import com.epam.brest.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Schedule {


    int count = 0;
    int block = 5;
    ArrayList<DaySchedule> scheduleObj = new ArrayList<>();

    public Schedule() {
    }

    public ArrayList<DaySchedule> createLectorRequestsList(List <String> groups, List <String> lectors, List <RequestsForGroupe> requestsForGroupes){

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

                        for (ArrayList<RequestsForGroupe> arr : listArr) {
                            for (RequestsForGroupe r : arr) {
                                System.out.print(r + "\t\t\t");
                            }
                            System.out.println();
                        }
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
                            DaySchedule ds = new DaySchedule(day, rg.getLector(), rg.getGroupe(), rg.getSubject(), pair);
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
}
