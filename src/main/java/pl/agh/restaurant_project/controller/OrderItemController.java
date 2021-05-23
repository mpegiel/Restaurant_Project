package pl.agh.restaurant_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.domain.Order;
import pl.agh.restaurant_project.domain.OrderItem;
import pl.agh.restaurant_project.repository.MenuRepository;
import pl.agh.restaurant_project.repository.OrderItemRepository;
import pl.agh.restaurant_project.service.OrderItemService;

import java.util.List;

@Controller
public class OrderItemController {
    private final OrderItemRepository orderItemRepo;
    private final OrderItemService orderItemService;

    @Autowired
    private MenuRepository menuRepo;

    public OrderItemController(OrderItemRepository orderItemRepo, OrderItemService orderItemService) {
        this.orderItemRepo = orderItemRepo;
        this.orderItemService = orderItemService;
    }

    @RequestMapping("/orderitems/create")
    public String showNewOrderItemPage(Model model) {
        OrderItem orderItem = new OrderItem();
        model.addAttribute("orderItem", orderItem);

        List<Menu> meals = menuRepo.findAll();
        model.addAttribute("allMeals", meals);

        return "/orderitems/create";
    }

    @RequestMapping(value = "/orderitems/save", method = RequestMethod.POST)
    public String saveOrderItem(@ModelAttribute("orderItem") OrderItem orderItem, Model model, BindingResult result) {
        orderItemService.save(orderItem);

        //model.addAttribute("orders", orderItemRepo.findAll() );
        return "redirect:/orders/create";
    }
}
