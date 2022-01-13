package com.epam.brest.service.rest;

import com.epam.brest.model.entity.LectorsSchedule;
import com.epam.brest.model.entity.StudentsSchedule;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.ScheduleDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ScheduleServiceRest implements ScheduleDtoService {

    private final Logger logger = LogManager.getLogger(ScheduleServiceRest.class);
    private String url;

    private RestTemplate restTemplate;

    public ScheduleServiceRest() {
    }

    public ScheduleServiceRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public int createScheduleDtoService() {
        logger.debug("Create schedule()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/create", Integer.class);
        return (Integer) responseEntity.getBody();
    }

    @Override
    public List<List<StudentsSchedule>> getScheduleForAllStudentsDtoService() {
        logger.debug("GetScheduleForAllStudents()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/allgroupes", List.class);
        return (List<List<StudentsSchedule>>) responseEntity.getBody();
    }

    @Override
    public List<LectorsSchedule> getScheduleForTeacherDtoService(String lector) {
        logger.debug("GetScheduleForLector()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/lector/" + lector, List.class);
        return (List<LectorsSchedule>) responseEntity.getBody();
    }

    @Override
    public List<StudentsSchedule> getScheduleForGroupeDtoService(String groupe) {
        return null;
    }
}
