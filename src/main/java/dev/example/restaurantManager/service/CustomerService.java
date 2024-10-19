package dev.example.restaurantManager.service;



import dev.example.restaurantManager.model.CustomerRestaurant;
import java.util.List;

public interface CustomerService {
    List<CustomerRestaurant> getAllCustomers();
    CustomerRestaurant createCustomer(CustomerRestaurant customer);
    CustomerRestaurant getCustomerById(String id);
    CustomerRestaurant updateCustomer(String id, CustomerRestaurant customerDetails);
    boolean deleteCustomer(String id);
    long countCustomers();
}

