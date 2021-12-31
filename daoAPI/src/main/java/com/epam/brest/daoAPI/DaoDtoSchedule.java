package com.epam.brest.daoAPI;

import com.epam.brest.model.entity.DaySchedule;

import java.util.List;

public interface DaoDtoSchedule {

    boolean createScheduleDto();

    List<DaySchedule> getScheduleForAll();

    List<DaySchedule> getScheduleForTeacherDto();

    List<DaySchedule> getScheduleForGroupeDto();

}