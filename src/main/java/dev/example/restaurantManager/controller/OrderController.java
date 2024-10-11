package dev.example.restaurantManager.controller;


import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/allOrders")
    public ResponseEntity<List<OrderRestaurant>> getAllOrders( ) {
        List<OrderRestaurant> orderRestaurants = orderService.getAllOrders();
        HttpHeaders headers = getCommonHeaders("Get all orders");

        return orderRestaurants != null && !orderRestaurants.isEmpty()
                ? new ResponseEntity<>(orderRestaurants, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderRestaurant> createOrder(@RequestBody OrderRestaurant orderRestaurant) {
        OrderRestaurant createdOrder = orderService.createOrder(orderRestaurant);
        HttpHeaders headers = getCommonHeaders("Create a new order");

        return createdOrder != null
                ? new ResponseEntity<>(createdOrder, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderRestaurant> updateOrder(@PathVariable String id, @RequestBody OrderRestaurant orderRestaurantDetails) {
        OrderRestaurant updatedOrder = orderService.updateOrder(id, orderRestaurantDetails);
        HttpHeaders headers = getCommonHeaders("Update a order");

        return updatedOrder != null
                ? new ResponseEntity<>(updatedOrder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        boolean deleted = orderService.deleteOrder(id);
        HttpHeaders headers = getCommonHeaders("Delete a order");
        headers.add("deleted", String.valueOf(deleted));

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderRestaurant> getOrderById(@PathVariable String id) {
        OrderRestaurant order = orderService.getOrderById(id);
        HttpHeaders headers = getCommonHeaders("Get an order");

        return order != null
                ? new ResponseEntity<>(order, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("menu-count", String.valueOf(orderService.countOrders()));
        headers.add("object", "customers");
        return headers;
    }
}
