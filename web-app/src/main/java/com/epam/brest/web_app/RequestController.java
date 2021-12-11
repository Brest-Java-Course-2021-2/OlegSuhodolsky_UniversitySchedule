package com.epam.brest.web_app;

import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

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


    @GetMapping("/newRequest/{id}")
    public String newRequest(@PathVariable("id") int id ,@ModelAttribute("request")  Request request  ) throws SQLException {
        // request = new Request();
        request.setId(id);
       // request.setDate(new Date());


      /*  model.addAttribute("request" , request);
        model.addAttribute("id", id);*/
        return "request/newRequest";
    }

    @PostMapping()
    public String createRequest(@ModelAttribute("request")  Request request)
    {
        System.out.println(request);
        request.setDate(new Date());
        requestService.createRequestService(request);


        return "redirect:/request/" + request.getId();
    }

}
