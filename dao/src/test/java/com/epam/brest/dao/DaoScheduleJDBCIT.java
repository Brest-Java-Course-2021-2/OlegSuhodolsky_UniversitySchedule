package com.epam.brest.dao;


import com.epam.brest.dao.schedulemodel.Schedule;
import com.epam.brest.model.entity.DaySchedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-jdbc-conf.xml"})
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback
public class DaoScheduleJDBCIT {

    private final Logger logger = LogManager.getLogger(DaoUserJDBCIT.class);

    private DaoGroupe daoGroupe;
    private DaoUser daoUser;
    private DaoRequest daoRequest;
    private DaoSchedule daoSchedule;
    private Schedule schedule;

    @Autowired
    public DaoScheduleJDBCIT(DaoGroupe daoGroupe, DaoUser daoUser, DaoRequest daoRequest,DaoSchedule daoSchedule, Schedule schedule) {
        this.daoSchedule = daoSchedule;
        this.daoGroupe = daoGroupe;
        this.daoUser = daoUser;
        this.daoRequest = daoRequest;
        this.schedule = schedule;

    }

    @Test
    void testFindDaoSchedule() {
        logger.debug("Execute test: TestDaoScheduleIsExist()");
        assertNotNull(daoUser);
        assertNotNull(daoRequest);
        assertNotNull(daoGroupe);
        assertNotNull(schedule);
        assertNotNull(daoSchedule);
    }


  /*  @Test
    void testWhereSchedule() {
        logger.debug("Execute test: IsScheduleInTable()");
        int count = daoSchedule.createScheduleDto();
        assertTrue(count > 0);
        List <DaySchedule> daySchedules = daoSchedule.getScheduleForAll();
        assertTrue(daySchedules.size() == count, "count = size");

        int count1 = daoSchedule.createScheduleDto();
        assertTrue(count1 > 0);
        List <DaySchedule> daySchedules1 = daoSchedule.getScheduleForAll();
        assertTrue(daySchedules1.size() == count, "count = size");

    }
*/


}
