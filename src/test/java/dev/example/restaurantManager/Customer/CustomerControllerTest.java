package dev.example.restaurantManager.Customer;

import dev.example.restaurantManager.controller.CustomerController;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(customerController, "endpointUrlCustomers", "/api/customers");
    }

    @Test
    void testShowEndpointCustomers() {
        String result = customerController.showEndpointCustomers();
        assertEquals("The customers endpoint URL is: /api/customers", result);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
        assertNotNull(response.getHeaders().get("desc"));
    }

/*    @Test
    void testCreateCustomer() {
        Customer customer = new Customer();
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customer, response.getBody());
        assertNotNull(response.getHeaders().get("desc"));
    }*/

 /*   @Test
    void testUpdateCustomer() {
        String id = "1";
        Customer customer = new Customer();
        when(customerService.updateCustomer(eq(id), any(Customer.class))).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.updateCustomer(id, customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        assertNotNull(response.getHeaders().get("desc"));
    }*/

    @Test
    void testDeleteCustomer() {
        String id = "1";
        when(customerService.deleteCustomer(id)).thenReturn(true);

        ResponseEntity<Void> response = customerController.deleteCustomer(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getHeaders().get("desc"));
        assertEquals("true", response.getHeaders().getFirst("deleted"));
    }

    @Test
    void testGetCustomerById() {
        String id = "1";
        Customer customer = new Customer();
        when(customerService.getCustomerById(id)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.getCustomerById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
        assertNotNull(response.getHeaders().get("desc"));
    }

    @Test
    public void testApi() {
        given()
                .when()
                .get("http://localhost:8080/api/v1/customer/allCustomers")
                .then()
                .statusCode(200);
    }
}