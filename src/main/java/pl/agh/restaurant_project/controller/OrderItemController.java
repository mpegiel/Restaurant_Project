package pl.agh.restaurant_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.domain.Order;
import pl.agh.restaurant_project.domain.OrderItem;
import pl.agh.restaurant_project.repository.MenuRepository;
import pl.agh.restaurant_project.repository.OrderItemRepository;
import pl.agh.restaurant_project.repository.OrderRepository;
import pl.agh.restaurant_project.service.OrderItemService;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderItemController {
    private final OrderItemRepository orderItemRepo;
    private final OrderItemService orderItemService;

    @Autowired
    private MenuRepository menuRepo;

    @Autowired
    private OrderRepository orderRepo;

    public OrderItemController(OrderItemRepository orderItemRepo, OrderItemService orderItemService) {
        this.orderItemRepo = orderItemRepo;
        this.orderItemService = orderItemService;
    }

    @RequestMapping("/orderitems/create/{id}")
    public String showNewOrderItemPage(Model model, @PathVariable("id") Optional<Long> orderId) {
        OrderItem orderItem = new OrderItem();
        if (orderId.isPresent()) {
            orderItem.setOrder(orderRepo.findById(orderId.get()).get());
            //System.out.println(orderId.get());
            //System.out.println(orderItem.getOrder().getId());
            //System.out.println("HEST\n\n");
        } else {
            //System.out.println("NE MA\n\n");
        }
        model.addAttribute("orderItem", orderItem);

        List<Menu> meals = menuRepo.findAll();
        model.addAttribute("allMeals", meals);
        model.addAttribute("orderId", orderId.get());

        return "/orderitems/create";
    }

    @RequestMapping(value = "/orderitems/save/{orderId}", method = RequestMethod.POST)
    public String saveOrderItem(@ModelAttribute("orderItem") OrderItem orderItem, @PathVariable("orderId") Long orderId, Model model, BindingResult result) {
        orderItem.setOrder(orderRepo.findById(orderId).get());
        //System.out.println(orderItem.getOrder().getId());
        orderItemService.save(orderItem);

        //model.addAttribute("orders", orderItemRepo.findAll() );
        return "redirect:/orders/edit/" + orderId.toString();
    }

    @RequestMapping("/orderitems/delete/{orderId}/{id}")
    public String deleteOrder(@PathVariable(name = "id") int id, @PathVariable("orderId") Long orderId) {
        orderItemService.delete(id);
        return "redirect:/orders/edit/" + orderId.toString();
    }
}
