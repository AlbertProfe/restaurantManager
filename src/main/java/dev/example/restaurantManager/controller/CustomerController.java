package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/allCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        HttpHeaders headers = getCommonHeaders("Get all customers");
        headers.add("customer-count", String.valueOf(customerService.countCustomers()));

        return customers != null && !customers.isEmpty()
                ? new ResponseEntity<>(customers, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        HttpHeaders headers = getCommonHeaders("Create a new customer");

        return createdCustomer != null
                ? new ResponseEntity<>(createdCustomer, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        HttpHeaders headers = getCommonHeaders("Update a customer");

        return updatedCustomer != null
                ? new ResponseEntity<>(updatedCustomer, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        boolean deleted = customerService.deleteCustomer(id);
        HttpHeaders headers = getCommonHeaders("Delete a customer");

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        HttpHeaders headers = getCommonHeaders("Get a customer by Id");

        return customer != null
                ? new ResponseEntity<>(customer, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        return headers;
    }
}