package pl.agh.restaurant_project.repository;

import pl.agh.restaurant_project.domain.Event;
import pl.agh.restaurant_project.domain.Link;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Long> {
    List<Link> findByTo(Event e);
    List<Link> findByFrom(Event e);
}