package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.OrderRepository;
import dev.example.restaurantManager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/orders")
@Controller
public class OrderWebController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/home")
    public String home(Model model) {
        List<OrderRestaurant> orders = orderRepository.findAll();
        System.out.println("Number of orders: " + orders.size());
        model.addAttribute("ordersToView", orders);
        return "menuOrder";
    }

}
