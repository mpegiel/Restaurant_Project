package pl.agh.restaurant_project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String home(ModelMap map)
    {
        map.put("welcome", "Hello on the website!"); //linked map to pass data to thymeleaf
        return "home";
    }
}
