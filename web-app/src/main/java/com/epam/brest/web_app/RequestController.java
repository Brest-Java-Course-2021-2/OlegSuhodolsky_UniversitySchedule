package com.epam.brest.web_app;

import com.epam.brest.serviceAPI.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class RequestController {

    private final RequestService requestService;
    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping("/{id}")
    public String indexRequest(@PathVariable("id") int id, Model model) {
        model.addAttribute("requests", requestService.getAllRequestsByIdService(id));
        model.addAttribute("id", id);

        return "request/indexRequest";
    }
}
