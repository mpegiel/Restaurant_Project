package pl.agh.restaurant_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.OrderItem;
import pl.agh.restaurant_project.repository.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepo;

    public List<OrderItem> getALlOrders() {
        return orderItemRepo.findAll();
    }

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }
    public void delete(long id) {
        orderItemRepo.deleteById(id);
    }
    public OrderItem get(Long id) {
        return orderItemRepo.findById(id).get();
    }

    public OrderItem update(Long ID, OrderItem updatedOrderItem) {
        Optional<OrderItem> optionalOrderItem = orderItemRepo.findById(ID);
        if (optionalOrderItem.isPresent()) {
            OrderItem orderItem = optionalOrderItem.get();
            //orderItem.setCode(updatedOrder.getCode());

            return orderItemRepo.save(orderItem);
        }
        return null;
    }

}