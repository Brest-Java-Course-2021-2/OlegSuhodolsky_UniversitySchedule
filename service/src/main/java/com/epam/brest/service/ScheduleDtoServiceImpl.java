package com.epam.brest.service;

import com.epam.brest.daoAPI.DaoDtoScheduleAPI;
import com.epam.brest.model.entity.LectorsSchedule;
import com.epam.brest.model.entity.StudentsSchedule;
import com.epam.brest.serviceAPI.ScheduleDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ScheduleDtoServiceImpl implements ScheduleDtoService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private DaoDtoScheduleAPI daoSchedule;

    public ScheduleDtoServiceImpl(DaoDtoScheduleAPI daoSchedule) {
        this.daoSchedule = daoSchedule;
    }

    @Override
    @Transactional
    public int createScheduleDtoService() {
        logger.info("Create schedule return -> number of pairs about requirements ");
        return daoSchedule.createScheduleDto();
    }

    @Override
    @Transactional(readOnly = true)
    public List<List<StudentsSchedule>> getScheduleForAllStudentsDtoService() {
        logger.info("Get schedule to all groupes by the week return -> List<List<StudentsSchedule>> ");
        return daoSchedule.getScheduleForAllStudents();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LectorsSchedule> getScheduleForTeacherDtoService(String lector) {
        logger.info("Get schedule for lectors  by the week return -> List<LectorsSchedule> ");
        return  daoSchedule.getScheduleForTeacherDto(lector);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentsSchedule> getScheduleForGroupeDtoService(String groupe) {
        logger.info("Get schedule for groupe by the week return -> List<StudentsSchedule> ");
        return daoSchedule.getScheduleForGroupeDto(groupe);
    }
}
