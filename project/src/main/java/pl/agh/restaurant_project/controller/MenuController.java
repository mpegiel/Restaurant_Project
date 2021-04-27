package pl.agh.restaurant_project.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.repository.MenuRepository;
import pl.agh.restaurant_project.service.MenuService;


@Controller
public class MenuController {
    private final MenuRepository repository;
    private final MenuService menuService;

    MenuController(MenuRepository  repository,MenuService menuService) {
        this.repository = repository;
        this.menuService=menuService;
    }

    @RequestMapping(value = "menu/all", method = RequestMethod.GET)
    public ModelAndView menus() {
        ModelAndView mav = new ModelAndView("menu/all");
        mav.addObject("menus", repository.findAll());
        return mav;
    }


    @RequestMapping("/menu/create")
    public String showNewMealPage(Model model) {
        Menu meal = new Menu();
        model.addAttribute("meal", meal);
        return "menu/create";
    }

    @RequestMapping(value = "/menusave", method = RequestMethod.POST)
    public String saveMeal(@ModelAttribute("meal") Menu meal, Model model, BindingResult result) {
        menuService.save(meal);

        model.addAttribute("menus", repository.findAll() );
        return "redirect:menu/all";
    }
    @RequestMapping("/menu/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
       menuService.delete(id);
        return "redirect:/menu/all";
    }


}
