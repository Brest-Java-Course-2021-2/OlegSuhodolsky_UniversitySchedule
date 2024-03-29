package com.epam.brest.web_app;

import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserController(UserService userService) {
        this.userService = userService;
        }

    @GetMapping(value = "/user")
    public String index(Model model) {
        model.addAttribute("user", userService.getAllUsersService());
        return "user/users";
    }

    @GetMapping(value = "/user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserByIdService(id));
        return "user/show";
    }

    @GetMapping(value = "/user/new")
    public String newUser(@ModelAttribute("user") User user) throws SQLException
    {
        return "user/new";
    }

    @PostMapping(value = "/user")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult result, Model model) throws SQLException {

        if (result.hasErrors()) {
            return "user/new";
        }
        userService.createUserService(user);
        model.addAttribute("user", user);
        return "redirect:/user";
    }

    @GetMapping(value = "/user/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserByIdService(id));
        return "user/edit";
    }

    @PostMapping(value = "/user/{id}")
    public String update(@ModelAttribute("user")@Valid User user,
                         BindingResult result,@PathVariable("id") int id) {

        if (result.hasErrors()) {
              return "user/edit";
        }

        user.setId(id);
        userService.updateUserService(user);
        return "redirect:/user/" + id;
    }

    @PostMapping(value = "/user/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserService(id);
        return "redirect:/user";
    }

    @GetMapping(value = "/user/count")
    public String count(Model model) {
        model.addAttribute("count", userService.countUserService());
        return "user/edit";
    }
}


