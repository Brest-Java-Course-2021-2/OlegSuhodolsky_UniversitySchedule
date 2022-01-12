package com.epam.brest.service;

import com.epam.brest.model.entity.LectorsSchedule;
import com.epam.brest.model.entity.StudentsSchedule;
import com.epam.brest.serviceAPI.ScheduleDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service-context-test.xml"})
@Transactional
public class ScheduleServiceDtoImplIT {
    private final Logger logger = LogManager.getLogger(ScheduleDtoServiceImpl.class);

    @Autowired
    ScheduleDtoService scheduleDtoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldScheduleService() {
        logger.info("If schedule {}");
        assertNotNull(scheduleDtoService);

    }

    @Test
    void shouldCreateSchedule() {
        logger.info("If schedule creste{}");
        int count = scheduleDtoService.createScheduleDtoService();
        assertTrue(count == 96, "dayschedules = 96");

        int count1 = scheduleDtoService.createScheduleDtoService();
        assertTrue(count1 == 96, "dayschedules1 = 96");
        assertTrue(count == count1, "Good creating schedule");
    }

    @Test
    void shouldScheduleForGroupe() {
        logger.info("If schedule for groupe creste{}");
        int count = scheduleDtoService.createScheduleDtoService();
        assertTrue(count == 96, "dayschedules = 96");
        List<StudentsSchedule> studentsSchedules = scheduleDtoService.getScheduleForGroupeDtoService("e1");
        assertTrue(studentsSchedules.size() == 5, "StudentsSchedules.size = 5");
    }

    @Test
    void shouldScheduleForLector() {
        logger.info("If schedule for lector create{}");
        int count = scheduleDtoService.createScheduleDtoService();
        assertTrue(count == 96, "dayschedules = 96");
        List<LectorsSchedule> lectorsSchedules = scheduleDtoService.getScheduleForTeacherDtoService("Mike");
        assertTrue(lectorsSchedules.size() == 5, "LectorsSchedules.size = 5");
    }

    @Test
    void shouldScheduleForAllStudents() {
        logger.info("If schedule for all students create{}");
        int count = scheduleDtoService.createScheduleDtoService();
        assertTrue(count == 96, "dayschedules = 96");
        List<List<StudentsSchedule>> schedule = scheduleDtoService.getScheduleForAllStudentsDtoService();
        assertTrue(schedule.size() == 8, "schedule.size = 8");
    }

}
