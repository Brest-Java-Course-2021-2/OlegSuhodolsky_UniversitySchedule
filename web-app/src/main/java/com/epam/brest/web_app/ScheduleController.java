package com.epam.brest.web_app;

import com.epam.brest.model.entity.LectorsSchedule;
import com.epam.brest.serviceAPI.ScheduleDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    private final ScheduleDtoService scheduleService;

    public ScheduleController(ScheduleDtoService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(value = "/create")
    public String create() {
        //model.addAttribute("user", userService.getAllUsersService());
        scheduleService.createScheduleDtoService();
        return "redirect:/user";
    }

    @GetMapping(value = "/schedule/lector/{lector}")
    public String getForLector(@PathVariable String lector, Model model) {
        //model.addAttribute("user", userService.getAllUsersService());
        List<LectorsSchedule> lectorSchedule = scheduleService.getScheduleForTeacherDtoService(lector);
        model.addAttribute("lectorSchedule", lectorSchedule);
        return "schedule/lector";
    }

}
