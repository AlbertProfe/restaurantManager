package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final RepositoryFacade repositoryFacade;

    @Autowired
    public CustomerServiceImpl(RepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repositoryFacade.customers().findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        return repositoryFacade.customers().save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        return repositoryFacade.customers().findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(String id, Customer customerDetails) {
        Customer customer = repositoryFacade.customers().findById(id).orElse(null);
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
            return repositoryFacade.customers().save(customer);
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(String id) {
        repositoryFacade.customers().deleteById(id);
        return repositoryFacade.customers().findById(id).isEmpty();
    }

    @Override
    public long countCustomers() {
        return repositoryFacade.customers().count();
    }
}