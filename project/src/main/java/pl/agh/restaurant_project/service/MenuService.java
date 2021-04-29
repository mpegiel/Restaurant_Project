package pl.agh.restaurant_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.domain.User;
import pl.agh.restaurant_project.repository.MenuRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
        MenuRepository menuRepository;


    public List<Menu> getALlMenu() {

        return menuRepository.findAll();
    }

    public Menu save(Menu meal) {
    return menuRepository.save(meal);
    }
    public void delete(long id) {
        menuRepository.deleteById(id);
    }
    public Menu get(Long id) {
        return menuRepository.findById(id).get();
    }
    public Menu update(Long ID,  Menu updatedMeal) {
        Optional<Menu> optionalMenu = menuRepository.findById(ID);

        if (optionalMenu.isPresent()) {
            Menu menu = optionalMenu.get();
            menu.setNameOfMeal(updatedMeal.getNameOfMeal());
            menu.setPrice(updatedMeal.getPrice());

            return menuRepository.save(menu);
        }
        return null;
    }

}