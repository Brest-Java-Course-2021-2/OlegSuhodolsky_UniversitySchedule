package com.epam.brest.web_app;

import com.epam.brest.model.entity.Admin;
import com.epam.brest.model.entity.User;
import com.epam.brest.serviceAPI.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        String login = "";
        String password = "";
        User user = new User();
        Admin admin = new Admin();
        model.addAttribute("login", login);
        model.addAttribute("password", password);
        model.addAttribute("user", user);
        model.addAttribute("admin", admin);
        return "login/showlogin";
    }

    @PostMapping("/authorization")
    public String authorization(@ModelAttribute("login") String login,
                                @ModelAttribute("password") String password, Model model) {

        return "login/authorization";
    }

    @PostMapping("/administration")
    public String authorizationAdmin(@ModelAttribute("login") String login,
                                     @ModelAttribute("password") String password,Model model) {

        return "login/administration";
    }

    @GetMapping("/checkadministration")
    public String enterAdminSystem(HttpServletRequest request, Model model) throws SQLException {
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
      /*  Admin admin = .showAdmin(login, password);
        if (admin == null) {
            return "redirect:/login";
        } else {
            return "redirect:/user";
        }*/
       return null;
    }

    @GetMapping("/testauthentication")
    public String enterSystem(HttpServletRequest request, Model model) throws SQLException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        List <User> users = userService.getAllUsersService();
        for (User u : users){
            if ((u.getLogin() == login) && (u.getPassword() == password)){
                user = u;
            }
        }
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "redirect:/request/" + user.getId();

    }


}
