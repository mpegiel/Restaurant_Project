package pl.agh.restaurant_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.agh.restaurant_project.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
