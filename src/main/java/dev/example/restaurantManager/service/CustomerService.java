package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer createCustomer(Customer customer);
    Customer getCustomerById(String id);
    Customer updateCustomer(String id, Customer customerDetails);
    boolean deleteCustomer(String id);
    long countCustomers();
}

