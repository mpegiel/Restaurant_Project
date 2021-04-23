package pl.agh.restaurant_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.UserRepository;
import pl.agh.restaurant_project.service.UserService;

import java.util.Objects;
import java.util.Optional;

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

        User oauthUser = userService.login(user.getUsername(), user.getPersonPassword() );
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

        model.addAttribute("users", userRepo.findAll() );
        return "redirect:users/all";
    }

    @RequestMapping(path = "/userupdate/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") Optional<Long> id, @ModelAttribute("user") User user, Model model, BindingResult result) {
        if (id.isPresent()) {
            userService.update(id.get(), user);
        }

        model.addAttribute("users", userRepo.findAll() );
        return "redirect:/users/all";
    }

    @RequestMapping("/users/edit/{id}")
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            User user = userService.get(id.get());
            model.addAttribute("user", user);
            model.addAttribute("userID", id.get());
        }

        return "/users/edit";
    }

    @RequestMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/users/all";
    }
}
