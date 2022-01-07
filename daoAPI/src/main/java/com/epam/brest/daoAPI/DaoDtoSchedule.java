package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.DaySchedule;
import com.epam.brest.model.entity.LectorsSchedule;
import com.epam.brest.model.entity.StudentsSchedule;

import java.util.List;

public interface DaoDtoSchedule {

   Integer createScheduleDto();

    List<StudentsSchedule> getScheduleForAllStudents();

    List<LectorsSchedule> getScheduleForTeacherDto();

    List<StudentsSchedule> getScheduleForGroupeDto();

}
