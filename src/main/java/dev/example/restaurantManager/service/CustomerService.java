package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements IService<Customer>{

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> getAllElements() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createElement(Customer element) {
        return customerRepository.save(element);
    }

    @Override
    public Customer getElementById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateElement(String id, Customer eDetails) {
        boolean isTrue = false;
        Customer customerElement = customerRepository.findById(id).orElse(null);
        if(customerElement != null){
            Field[] fields = customerElement.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(customerElement) != field.get(eDetails)){
                        isTrue = true;
                        field.set(customerElement,field.get(eDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            customerRepository.save(customerElement);
        }

        return isTrue ? customerElement : eDetails;
    }

    @Override
    public boolean deleteElement(String id) {
        customerRepository.deleteById(id);
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.isEmpty()
                ? false : true ;
    }

    @Override
    public long countElements() {
        return customerRepository.count();
    }
}
