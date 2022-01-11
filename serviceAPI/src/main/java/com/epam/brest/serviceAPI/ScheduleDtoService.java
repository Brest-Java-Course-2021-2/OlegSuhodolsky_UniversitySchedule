package com.epam.brest.serviceAPI;

import com.epam.brest.model.entity.LectorsSchedule;
import com.epam.brest.model.entity.StudentsSchedule;

import java.util.List;

public interface ScheduleDtoService {

    int createScheduleDtoService();

    List<List<StudentsSchedule>> getScheduleForAllStudentsDtoService();

    List<LectorsSchedule> getScheduleForTeacherDtoService(String lector);

    List<StudentsSchedule> getScheduleForGroupeDtoService(String groupe);
}
