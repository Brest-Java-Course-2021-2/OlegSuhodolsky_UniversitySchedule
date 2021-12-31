package com.epam.brest.web_app;

import com.epam.brest.serviceAPI.SerializationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.InvalidObjectException;

@Controller
public class SerializatorController {

    private final SerializationService serializationService;
    private static final Logger logger = LoggerFactory.getLogger(SerializatorController.class);

    int isLoad = 0;

    public SerializatorController(SerializationService serializationService) {
        this.serializationService = serializationService;
    }


    @GetMapping(value = "/serializator/save")
    public String writeDtaToFile() throws InvalidObjectException {
        logger.debug("write data to file");
        serializationService.writeListUserService();
        return "redirect:/user";
    }

    @GetMapping(value = "/serializator/load")
    public String writeDtaFrom() throws InvalidObjectException {
        logger.debug("write data from file");
        if (isLoad == 0){
            isLoad++;
            serializationService.getListUserService();
        }
        return "redirect:/user";
    }

}
