package com.epam.brest.web_app;


import com.epam.brest.dao.DaoUser;
import com.epam.brest.serviceAPI.UserService;
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


    //private final DaoUser userDAO;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
        model.addAttribute("user", userService.getAllUsersService());
        return "user/index111";
    }


}


