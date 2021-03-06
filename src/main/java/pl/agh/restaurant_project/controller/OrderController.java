package pl.agh.restaurant_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import pl.agh.restaurant_project.domain.Menu;
import pl.agh.restaurant_project.domain.Order;
import pl.agh.restaurant_project.domain.OrderItem;
import pl.agh.restaurant_project.domain.Warehouse;
import pl.agh.restaurant_project.repository.MenuRepository;
import pl.agh.restaurant_project.repository.OrderItemRepository;
import pl.agh.restaurant_project.repository.OrderRepository;
import pl.agh.restaurant_project.service.OrderItemService;
import pl.agh.restaurant_project.service.OrderService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    private final OrderRepository orderRepo;
    private final OrderService orderService;

    @Autowired
    private OrderItemRepository orderItemRepo;
    @Autowired
    private OrderItemService orderItemService;

    public OrderController(OrderRepository orderRepo, OrderService orderService) {
        this.orderRepo = orderRepo;
        this.orderService = orderService;
    }

    @RequestMapping(value = "orders/all", method = RequestMethod.GET)
    public ModelAndView orders() {
        ModelAndView mav = new ModelAndView("orders/all");
        mav.addObject("orders", orderRepo.findAll());
        return mav;
    }

    @RequestMapping("/orders/create")
    public String showNewOrderPage(Model model) {
        Order order = new Order();
        orderService.save(order);

        //List<OrderItem> orderItems = orderItemRepo.findAll();
        //model.addAttribute("orderItems", orderItems);
        model.addAttribute("orders", orderRepo.findAll() );
        return "redirect:/orders/all";
    }

    @RequestMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable(name = "id") int id) {
        orderService.delete(id);
        return "redirect:/orders/all";
    }

    @RequestMapping(path = "/orders/update/{id}", method = RequestMethod.POST)
    public String updateOrder(@PathVariable("id") Optional<Long> id, @ModelAttribute("order") Order order, Model model, BindingResult result) {
        if (id.isPresent()) {
            orderService.update(id.get(), order);
        }

        model.addAttribute("orders", orderRepo.findAll() );
        return "redirect:/orders/all";
    }

    @RequestMapping("/orders/edit/{id}")
    public String editOrderById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Order order = orderService.get(id.get());
            model.addAttribute("order", order);
            model.addAttribute("orderId", id.get());

            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);//order.getOrderItems();
            model.addAttribute("orderItems", orderItems);

            double wholePrice = 0;
            for (OrderItem item : orderItems) {
                wholePrice += item.getMeal().getPrice() * item.getQuantity();
            }
            model.addAttribute("wholePrice", wholePrice);
        }

        return "/orders/edit";
    }

//    @RequestMapping("/orders/check/{id}")
//    public void downloadCheck(HttpServletResponse response) throws IOException {
//
//        String csvFileName = "check.csv";
//
//        response.setContentType("text/csv");
//        String headerKey = "Content-Disposition";
//        String headerValue = String.format("attachment; filename=\"%s\"",
//                csvFileName);
//        response.setHeader(headerKey, headerValue);
//
//        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);
//
//        List<Warehouse> tmp= repository.findAll();
//        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
//                CsvPreference.STANDARD_PREFERENCE);
//
//        String[] header = { "product", "quantity", "price" };
//
//        csvWriter.writeHeader(header);
//
//        for (OrderItem item : orderItems) {
//            csvWriter.write(item, header);
//        }
//
//        csvWriter.close();
//    }
}
