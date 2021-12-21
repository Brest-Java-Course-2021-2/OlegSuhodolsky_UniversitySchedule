package com.epam.brest.web_app;

import com.epam.brest.model.entity.Request;
import com.epam.brest.serviceAPI.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Date;

@Controller
//@RequestMapping("/request")
public class RequestController {

    private final RequestService requestService;
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

   // @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping(value = "/request/{id}")
    public String indexRequest(@PathVariable("id") int id, Model model) {
        logger.debug("getAllRequests({}, {})");
        model.addAttribute("requests", requestService.getAllRequestsByIdService(id));
        model.addAttribute("id", id);
       // model.addAttribute("idR", id);
        return "request/indexRequest";
    }


    @GetMapping(value = "/request/newRequest/{id}")
    public String newRequest( @PathVariable("id") int id, @ModelAttribute("request")  Request request ) throws SQLException {
        logger.debug("newRequest({}, {})");
        //Request request = new Request();

        request.setId(id);
        request.setDate(new Date());

       // model.addAttribute("request" , request);
        System.out.println("GET METHOD success " + request);
              return "request/newRequest";
    }

    @CrossOrigin
    @PostMapping("/requestnew")
    public String createRequest(@ModelAttribute("request")  Request request)throws SQLException
    {
        logger.debug("newRequest({}, {}) prepared  ", request);
        System.out.println("POST METHOD" + request);
        int id = request.getId();
        requestService.createRequestService(request);
        //model.addAttribute("request", request);
        logger.debug("newRequest({}, {}) success  ", request);
        return "redirect:/request/" + id;
    }

    @GetMapping(value = "/request/{id}/edit")
    public String edit(@ModelAttribute("request")  Request request, @PathVariable("id") int id, Model model) {
        System.out.println("GET REQUEST model " + request);
        logger.debug("EditRequest({}, {}) prepare  ", request);
        request = requestService.getRequestByIdService(id);
        model.addAttribute("request", request);
        System.out.println("GET REQUEST BY IDR " + request);
        // request.setDate(new Date());
        //request.setDate(new Date());
        logger.debug("EditRequest({}, {}) send to POST ", request);
        /*model.addAttribute("request", request);
        model.addAttribute("id", idR);*/
        return "request/edit";
    }

    @CrossOrigin
    @PostMapping (value = "/requestedit")
    public String updateRequest(@ModelAttribute("request")  Request request) {
        //requestService.getRequestByIdService(idR);
        logger.debug("EditRequest({}, {}) int POST METHOD  ", request);
        System.out.println(request);

        requestService.updateRequestService(request);
        logger.debug("EditRequest({}, {}) succes", request);

        return "redirect:/request/" + request.getId();
    }

    @CrossOrigin
    @PostMapping(value = "/request/{id}/delete")
    public String delete(@PathVariable("id") int idR, @ModelAttribute("id") int id) {
        //Request request = requestService.getRequestByIdService(id);
        System.out.println("Deleting request " + id);
        requestService.deleteRequestService(idR);
        return "redirect:/request/" +id;
    }
}
