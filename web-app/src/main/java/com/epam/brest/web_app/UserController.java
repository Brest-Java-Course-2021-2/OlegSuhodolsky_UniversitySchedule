package com.epam.brest.web_app;



import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;

/**
 * Hello MVC controller.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //private final DaoUser userDAO;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", userService.getAllUsersService());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserByIdService(id));
        return "user/show";
    }


    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) throws SQLException
    {
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user, Model model) throws SQLException {
        // Create record in DAO

        userService.createUserService(user);
         //Model model = null;
        model.addAttribute("user", user);

        return "redirect:/user";
    }



    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserByIdService(id));
        return "user/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        user.setId(id);
        userService.updateUserService(user);
        return "redirect:/user";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserService(id);
        return "redirect:/user";
    }



}


