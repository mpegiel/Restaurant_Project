package pl.agh.restaurant_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.restaurant_project.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
