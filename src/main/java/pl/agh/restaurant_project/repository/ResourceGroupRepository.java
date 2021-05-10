package pl.agh.restaurant_project.repository;


import pl.agh.restaurant_project.domain.ResourceGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceGroupRepository extends CrudRepository<ResourceGroup, Long> {

    public List<ResourceGroup> findAllByOrderByOrdinal();

}