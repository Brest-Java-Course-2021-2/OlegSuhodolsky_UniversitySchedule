package com.epam.brest.web_app;


import com.epam.brest.dao.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Hello MVC controller.
 */
@Controller

public class
UserController {


    private final DaoUser userDAO;

    @Autowired
    public UserController(DaoUser userDAO) {
        this.userDAO = userDAO;
    }


    @GetMapping(value = "/hello")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                        Model model) {

        System.out.println("hello(name:'" + name + "')");
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/user")
    public String index(Model model) {
        model.addAttribute("user", userDAO.getAllUsers());
        return "user/index111";
    }


}


