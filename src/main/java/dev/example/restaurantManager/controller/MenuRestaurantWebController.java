package dev.example.restaurantManager.controller;

import org.springframework.ui.Model;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/menus")
@Controller
public class MenuRestaurantWebController {

    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/home")
    public String home(Model model) {

        List<MenuRestaurant> menus = menuRepository.findAll();
        model.addAttribute("menuToView", menus);

        return "menuHome";
        }
}


