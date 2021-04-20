package pl.agh.restaurant_project.repository;

import java.util.List;
import pl.agh.restaurant_project.domain.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Long>{
    List<Menu> findByNameOfMeal(String nameOfMeal);

    Menu findById(long id);
}
