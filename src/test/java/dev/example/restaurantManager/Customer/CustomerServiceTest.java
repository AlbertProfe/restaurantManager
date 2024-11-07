package dev.example.restaurantManager;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    private Customer testCustomer;

    @BeforeEach
    public void setup() {
        testCustomer = new Customer("1", "Alex", "alex@example.com",
                "1234567890", 30, false, false);
    }

    @Test
    public void createCustomerServiceTest() {
        Customer customerCreated = customerService.createCustomer(testCustomer);

        assertThat(customerCreated).isNotNull();
        assertThat(customerCreated.getId()).isNotNull();
        assertThat(customerCreated.getName()).isEqualTo(testCustomer.getName());
    }

    @Test
    public void getCustomerByIdTest() {
        Customer savedCustomer = customerService.createCustomer(testCustomer);
        Customer retrievedCustomer = customerService.getCustomerById(savedCustomer.getId());

        assertThat(retrievedCustomer).isNotNull();
        assertThat(retrievedCustomer.getId()).isEqualTo(savedCustomer.getId());
    }

    @Test
    public void getAllCustomersTest() {
        customerService.createCustomer(testCustomer);
        Customer anotherCustomer = new Customer("2", "Bob", "bob@example.com",
                "0987654321", 25, true, false);
        customerService.createCustomer(anotherCustomer);

        List<Customer> customers = customerService.getAllCustomers();

        assertThat(customers).isNotNull();
        assertThat(customers.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    public void updateCustomerTest() {
        // First, create a customer
        Customer savedCustomer = customerService.createCustomer(testCustomer);
        String customerId = savedCustomer.getId();

        // Create a new Customer object with updated details
        Customer updatedDetails = new Customer();
        updatedDetails.setName("Updated Alex");
        updatedDetails.setEmail("updated.alex@example.com");
        updatedDetails.setPhoneNumber("9876543210");

        // Call the updateCustomer method
        Customer updatedCustomer = customerService.updateCustomer(customerId, updatedDetails);

        // Assertions
        assertThat(updatedCustomer).isNotNull();
        assertThat(updatedCustomer.getId()).isEqualTo(customerId);
        assertThat(updatedCustomer.getName()).isEqualTo("Updated Alex");
        assertThat(updatedCustomer.getEmail()).isEqualTo("updated.alex@example.com");
        assertThat(updatedCustomer.getPhoneNumber()).isEqualTo("9876543210");

        // The age should remain unchanged
        assertThat(updatedCustomer.getAge()).isEqualTo(testCustomer.getAge());

        // Verify that the customer was actually updated in the repository
        Customer retrievedCustomer = customerService.getCustomerById(customerId);
        assertThat(retrievedCustomer).isEqualToComparingFieldByField(updatedCustomer);
    }

    @Test
    public void deleteCustomerTest() {
        Customer savedCustomer = customerService.createCustomer(testCustomer);
        String customerId = savedCustomer.getId();

        customerService.deleteCustomer(customerId);

        Optional<Customer> deletedCustomer = customerRepository.findById(customerId);
        assertThat(deletedCustomer).isEmpty();
    }

    @Test
    public void customerNotFoundTest() {
        String nonExistentId = "nonexistent";
        assertThrows(RuntimeException.class, () -> customerService.getCustomerById(nonExistentId));
    }
}