package com.epam.brest.web_app;

import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/request")
public class RequestController {

    private final RequestService requestService;
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping("/{id}")
    public String indexRequest(@PathVariable("id") int id, Model model) {
        logger.debug("getAllRequests({}, {})");
        model.addAttribute("requests", requestService.getAllRequestsByIdService(id));
        model.addAttribute("id", id);
        model.addAttribute("idR", id);
        return "request/indexRequest";
    }


    @GetMapping("/newRequest/{id}")
    public String newRequest(@PathVariable("id") int id ,@ModelAttribute("request")  Request request  ) throws SQLException {
        logger.debug("newRequest({}, {})", request);
              request.setId(id);
              return "request/newRequest";
    }

    @PostMapping()
    public String createRequest(@ModelAttribute("request")  Request request)
    {
        System.out.println(request);
        request.setDate(new Date());
        requestService.createRequestService(request);
        logger.debug("newRequest({}, {}) succes", request);
        return "redirect:/request/" + request.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int idR) {
        Request request = requestService.getRequestByIdService(idR);
        request.setDate(new Date());
        logger.debug("EditRequest({}, {})", request);
        model.addAttribute("request", request);
        model.addAttribute("id", idR);
        return "request/edit";
    }

    @PostMapping("/{id}")
    public String updateRequest(@ModelAttribute("request")  Request request, @PathVariable("id") int idR) {
        //requestService.getRequestByIdService(idR);
        System.out.println(request);
        request.setDate(new Date());
        requestService.updateRequestService(request);
        logger.debug("EditRequest({}, {}) succes", request);

        return "redirect:/request/" + request.getId();
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") int idR) {
        Request request = requestService.getRequestByIdService(idR);
        requestService.deleteRequestService(idR);
        return "redirect:/request/" + request.getId();
    }
}
