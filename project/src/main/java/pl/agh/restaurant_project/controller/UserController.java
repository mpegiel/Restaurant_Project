package pl.agh.restaurant_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.UserRepository;
import pl.agh.restaurant_project.service.UserService;

import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    private UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user ) {

        User oauthUser = userService.login(user.getPersonLogin(), user.getPersonPassword() );
        System.out.print(oauthUser);
        if(Objects.nonNull(oauthUser)) {
            return "redirect:/";
        }
        else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "users/all", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView mav = new ModelAndView("users/all");
        mav.addObject("users", userRepo.findAll());
        return mav;
    }

    @RequestMapping("/users/create")
    public String showNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/create";
    }

    @RequestMapping(value = "/usersave", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, Model model, BindingResult result) {
        userService.save(user);

        model.addAttribute("menus", userRepo.findAll() );
        return "redirect:users/all";
    }
}
