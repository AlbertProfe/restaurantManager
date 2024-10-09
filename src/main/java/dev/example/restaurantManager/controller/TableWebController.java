package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Table;
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

    // CRUD for table
    @GetMapping("/table/home")
    public String home(Model model) {
        List<Table> tables = tableRepository.findAll();
        model.addAttribute("tablesToView", tables);
        return "tableHome";
    }
}


