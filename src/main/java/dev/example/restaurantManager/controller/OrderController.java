package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;
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

    // manage request by ResponseEntity with all orders
    @GetMapping("/allOrders")
    public ResponseEntity<List<OrderRestaurant>> getAllOrders( ) {
        List<OrderRestaurant> orders = orderService.getAllOrders();
        HttpHeaders headers = getCommonHeaders("Get all orders");

        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<OrderRestaurant> createOrder(@RequestBody OrderRestaurant order) {
        OrderRestaurant createdOrder = orderService.createOrder(order);
        HttpHeaders headers = getCommonHeaders("Create a new order");

        return createdOrder != null
                ? new ResponseEntity<>(createdOrder, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderRestaurant> updateOrder(@PathVariable String id, @RequestBody OrderRestaurant orderDetails) {
        OrderRestaurant updatedOrder = orderService.updateOrder(id, orderDetails);
        HttpHeaders headers = getCommonHeaders("Update an order");

        return updatedOrder != null
                ? new ResponseEntity<>(updatedOrder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        boolean deleted = orderService.deleteOrder(id);
        HttpHeaders headers = getCommonHeaders("Delete an order");
        headers.add("deleted", String.valueOf(deleted));


        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}/menus/{menus}")
    public ResponseEntity<OrderRestaurant> updateOrderAddMenuQty(@PathVariable String id, @RequestBody List<MenuRestaurant> menus) {
        OrderRestaurant orderUpdated = orderService.addMenus(id,menus);
        // OrderRestaurant order = orderService.getOrderById(id);
        HttpHeaders headers = getCommonHeaders("Add menu qties to order");
        return orderUpdated != null
                ? new ResponseEntity<>(orderUpdated, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
//        if( orderUpdated==null ){
//            return  new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
//        }
//        for (MenuRestaurant m : menus) {
//            order.addMenu(m);
//        }
        // OrderRestaurant orderUpdated = orderService.updateOrder(id,order);

    }

    @DeleteMapping("/{id}/menus/{menus}")
    public ResponseEntity<OrderRestaurant> updateOrderDelMenuQty(@PathVariable String id, @RequestBody List<MenuRestaurant> menus) {
        OrderRestaurant orderUpdated = orderService.deleteMenus(id,menus);
        HttpHeaders headers = getCommonHeaders("Delete menu qties from order");
        return orderUpdated != null
                ? new ResponseEntity<>(orderUpdated, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderRestaurant> getOrderById(@PathVariable String id) {
        OrderRestaurant order = orderService.getOrderById(id);
        HttpHeaders headers = getCommonHeaders("Get an order by Id");

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
        headers.add("order-count", String.valueOf(orderService.countOrders()));
        headers.add("object", "orders");
        return headers;
    }


}
