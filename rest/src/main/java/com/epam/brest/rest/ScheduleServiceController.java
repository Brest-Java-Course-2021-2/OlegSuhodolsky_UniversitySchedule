package com.epam.brest.rest;

import com.epam.brest.dao.DaoSchedule;
import com.epam.brest.model.entity.LectorsSchedule;
import com.epam.brest.model.entity.StudentsSchedule;
import com.epam.brest.serviceAPI.ScheduleDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleServiceController {

    private static final Logger logger = LogManager.getLogger(DaoSchedule.class);
    private final ScheduleDtoService scheduleService;

    public ScheduleServiceController(ScheduleDtoService scheduleDtoService) {
        this.scheduleService = scheduleDtoService;
    }

    @GetMapping(value = "/schedule/create")
    public final int createSchedule() {
        logger.debug("Schedule create () ");
        return scheduleService.createScheduleDtoService();
    }

    @GetMapping(value = "/schedule/groupe/{groupe}")
    public final List<StudentsSchedule> getScheduleGroupe(@PathVariable String groupe) {
        logger.debug("Schedule for groupe () ");
        return scheduleService.getScheduleForGroupeDtoService(groupe);
    }

    @GetMapping(value = "/schedule/lector/{lector}")
    public final List<LectorsSchedule> getScheduleLector(@PathVariable String lector) {
        logger.debug("Schedule for lector () ");
        return scheduleService.getScheduleForTeacherDtoService(lector);
    }

    @GetMapping(value = "/schedule/allgroupes")
    public final List<List<StudentsSchedule>> getScheduleGroupe() {
        logger.debug("Schedule for all groupes () ");
        return scheduleService.getScheduleForAllStudentsDtoService();
    }
}
