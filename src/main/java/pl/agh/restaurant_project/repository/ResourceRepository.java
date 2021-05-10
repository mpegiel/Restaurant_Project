package pl.agh.restaurant_project.repository;


import pl.agh.restaurant_project.domain.Resource;
import pl.agh.restaurant_project.domain.ResourceGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
    public List<Resource> findByParent(ResourceGroup parent);
}