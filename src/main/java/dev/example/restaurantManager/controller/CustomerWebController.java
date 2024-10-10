package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/web")
@Controller
public class CustomerWebController {

    @Autowired
    ICustomerRepository customerRepository;

    // CRUD for customer
    @GetMapping("/home")
    public String home(Model model) {

        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customersToView", customers);

        return "home";
    }


}
