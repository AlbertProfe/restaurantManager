package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.repository.MenuRepository;
import dev.example.restaurantManager.service.MenuRestaurantService;
import dev.example.restaurantManager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/menus")
@Controller
public class MenuWebController {

    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/home")
    public String home(Model model) {

        List<MenuRestaurant> menus = menuRepository.findAll();
        model.addAttribute("customersToView", menus);

        return "menuHome";
    }
}
