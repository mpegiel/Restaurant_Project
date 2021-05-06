package pl.agh.restaurant_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.restaurant_project.domain.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
