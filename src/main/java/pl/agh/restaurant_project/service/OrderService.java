package pl.agh.restaurant_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.restaurant_project.domain.Order;
import pl.agh.restaurant_project.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepo;

    public List<Order> getALlOrders() {
        return orderRepo.findAll();
    }

    public Order save(Order order) {
        return orderRepo.save(order);
    }
    public void delete(long id) {
        orderRepo.deleteById(id);
    }
    public Order get(Long id) {
        return orderRepo.findById(id).get();
    }
    public Order update(Long ID, Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepo.findById(ID);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setCode(updatedOrder.getCode());

            return orderRepo.save(order);
        }
        return null;
    }

}