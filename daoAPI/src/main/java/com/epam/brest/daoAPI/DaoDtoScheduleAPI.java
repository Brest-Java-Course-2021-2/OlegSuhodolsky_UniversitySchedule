package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.LectorsSchedule;
import com.epam.brest.model.entity.StudentsSchedule;

import java.util.List;

public interface DaoDtoScheduleAPI {

    int createScheduleDto();

    List<List<StudentsSchedule>> getScheduleForAllStudents();

    List<LectorsSchedule> getScheduleForTeacherDto(String lector);

    List<StudentsSchedule> getScheduleForGroupeDto(String groupe);

}
