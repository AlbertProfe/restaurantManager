package dev.example.restaurantManager.service;


import dev.example.restaurantManager.model.CustomerRestaurant;
import dev.example.restaurantManager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerRestaurant> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerRestaurant createCustomer(CustomerRestaurant customer) {
        customer.setId(UUID.randomUUID().toString());
        return customerRepository.save(customer);
    }

    @Override
    public CustomerRestaurant getCustomerById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerRestaurant updateCustomer(String id, CustomerRestaurant customerDetails) {
        CustomerRestaurant customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            if (customerDetails.getName() != null) {
                customer.setName(customerDetails.getName());
            }
            if (customerDetails.getEmail() != null) {
                customer.setEmail(customerDetails.getEmail());
            }
            if (customerDetails.getPhoneNumber() != null) {
                customer.setPhoneNumber(customerDetails.getPhoneNumber());
            }
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(String id) {
        customerRepository.deleteById(id);
        Optional<CustomerRestaurant> customer = customerRepository.findById(id);
        return customer.isEmpty()
                ? false : true ;
    }

    @Override
    public long countCustomers() {
        return customerRepository.count();
    }


}