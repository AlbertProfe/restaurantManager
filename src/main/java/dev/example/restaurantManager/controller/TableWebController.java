package dev.example.restaurantManager.controller;

import org.springframework.ui.Model;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/table")
@Controller
public class TableWebController {

    @Autowired
    TableRepository tableRepository;

    @RequestMapping("/home")
    public String home(Model model) {
        List<TableRestaurant> tables = tableRepository.findAll();
        model.addAttribute("tables", tables);
        return "menuTable";
    }
}