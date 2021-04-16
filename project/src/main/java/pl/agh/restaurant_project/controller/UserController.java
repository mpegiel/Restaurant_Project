package pl.agh.restaurant_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user ) {

        Optional<User> oauthUser = userService.login(user.getUsername(), user.getPassword() );
        System.out.print(oauthUser);
        if(Objects.nonNull(oauthUser)) {
            return "redirect:/";
        }
        else {
            return "redirect:/login";
        }
    }


}
