package pl.agh.restaurant_project.repository;

import java.beans.JavaBean;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.restaurant_project.domain.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
