package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/web")
@Controller
public class TableWebController {

    @Autowired
    private TableRepository tableRepository;

    @GetMapping("/table/home")
    public String home(Model model) {
        List<TableRestaurant> tableRestaurants = tableRepository.findAll();
        model.addAttribute("tablesToView", tableRestaurants);
        return "tableHome";
    }
}


