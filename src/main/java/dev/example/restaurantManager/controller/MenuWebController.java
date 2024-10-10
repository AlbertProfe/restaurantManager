package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/web")
@Controller
public class MenuWebController {

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/menu/home")
    public String home(Model model) {
        List<Menu> menus = menuRepository.findAll();
        model.addAttribute("menusToView", menus);
        return "menuHome";
    }
}

