package com.epam.brest.web_app;

import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Date;

@Controller
public class RequestController {

    private final RequestService requestService;
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
    int idRequest = 0;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/request/{id}")
    public String indexRequest(@PathVariable("id") int id, Model model) {
        logger.debug("getAllRequests({}, {})");
        idRequest = id;
        model.addAttribute("requests", requestService.getAllRequestsByIdService(id));
        model.addAttribute("id", id);
        return "request/indexRequest";
    }

    @GetMapping(value = "/request/newRequest/{id}")
    public String newRequest( @PathVariable("id") int id, @ModelAttribute("request")  Request request ) throws SQLException {
        logger.debug("newRequest({}, {})");
        request.setId(id);
        request.setDate(new Date());
        return "request/newRequest";
    }

    //@CrossOrigin
    @PostMapping("/requestnew")
    public String createRequest(@ModelAttribute("request") @Valid Request request, BindingResult result)throws SQLException
    {
        logger.debug("newRequest({}, {}) prepared  ", request);

        if (result.hasErrors()) {
            return "request/newRequest";
        }
        int id = request.getId();
        requestService.createRequestService(request);
        logger.debug("newRequest({}, {}) success  ", request);
        return "redirect:/request/" + id;
    }

    @GetMapping(value = "/request/{id}/edit")
    public String edit(@ModelAttribute("request")  Request request, @PathVariable("id") int id, Model model) {
        logger.debug("EditRequest({}, {}) prepare  ", request);
        request = requestService.getRequestByIdService(id);
        model.addAttribute("request", request);
        logger.debug("EditRequest({}, {}) send to POST ", request);
        return "request/edit";
    }

    @CrossOrigin
    @PostMapping (value = "/requestedit")
    public String updateRequest(@ModelAttribute("request") @Valid Request request, BindingResult result) {
        logger.debug("EditRequest({}, {}) int POST METHOD  ", request);
         if (result.hasErrors()) {
            return "request/edit";
        }

        requestService.updateRequestService(request);
        logger.debug("EditRequest({}, {}) succes", request);
        return "redirect:/request/" + request.getId();
    }

    @CrossOrigin
    @PostMapping(value = "/request/delete/{id}")
    public String delete(@PathVariable ("id") int id) {
        requestService.deleteRequestService(id);
        logger.debug("DeleteRequest({}, {}) succes", id);
        return "redirect:/request/" + idRequest;
    }
}
