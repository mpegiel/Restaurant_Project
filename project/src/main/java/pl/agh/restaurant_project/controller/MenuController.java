package pl.agh.restaurant_project.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.repository.MenuRepository;

@Controller
public class MenuController {
    private final MenuRepository repository;

    MenuController(MenuRepository  repository) {
        this.repository = repository;
    }

    @GetMapping("/menu")
    List<Menu> all() {
        return (List<Menu>) repository.findAll();
    }
}
