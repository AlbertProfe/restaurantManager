package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Menu;
import java.util.List;

public interface MenuService {
    List<Table> getAllCustomers();
    Customer createCustomer(Customer customer);
    Customer getCustomerById(String id);
    Customer updateCustomer(String id, Customer customerDetails);
    boolean deleteCustomer(String id);
    long countCustomers();
}
