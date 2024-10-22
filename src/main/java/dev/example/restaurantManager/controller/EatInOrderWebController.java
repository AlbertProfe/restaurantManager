package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/eatInOrder")
@Controller
public class EatInOrderWebController {

    @Autowired
    EatInOrderRepository eatInOrderRepository;

    @GetMapping("home")
    public String home(Model model) {
        List<EatInOrderRestaurant> eatInOrders = eatInOrderRepository.findAll();
        model.addAttribute("eatInOrders", eatInOrders);
        return "menuEatInOrder";
    }
}
