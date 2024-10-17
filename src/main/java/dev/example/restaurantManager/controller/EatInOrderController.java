package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.EatInOrder;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.EatInOrderService;
import dev.example.restaurantManager.service.TableRestaurantService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/eatInOrder")
@RestController
public class EatInOrderController {

    @Autowired
    private EatInOrderService eatInOrderService;

    @Autowired
    private TableRestaurantService tableRestaurantService;

    @GetMapping("/allOrders")
    public ResponseEntity<List<EatInOrder>> getAllOrders() {
        List<EatInOrder> eatInOrders = eatInOrderService.getAllOrders();
        HttpHeaders headers = getCommonHeaders("Get all eat-in orders");
        return eatInOrders != null && !eatInOrders.isEmpty()
                ? new ResponseEntity<>(eatInOrders, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EatInOrder> createOrder(@RequestBody EatInOrder eatInOrder) {
        EatInOrder createdEatInOrder = eatInOrderService.createOrder(eatInOrder);
        HttpHeaders headers = getCommonHeaders("Create a new eat-in order");

        return createdEatInOrder != null
                ? new ResponseEntity<>(createdEatInOrder, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EatInOrder> updateOrder(@PathVariable String id, @RequestBody EatInOrder eatInOrderDetails) {
        EatInOrder updatedEatInOrder = eatInOrderService.updateOrder(id, eatInOrderDetails);
        HttpHeaders headers = getCommonHeaders("Update an eat-in order");

        return updatedEatInOrder != null
                ? new ResponseEntity<>(updatedEatInOrder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        boolean deleted = eatInOrderService.deleteOrder(id);
        HttpHeaders headers = getCommonHeaders("Delete an eat-in order");
        headers.add("deleted", String.valueOf(deleted));

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EatInOrder> getOrderById(@PathVariable String id) {
        EatInOrder eatInOrder = eatInOrderService.getOrderById(id);
        HttpHeaders headers = getCommonHeaders("Get an eat-in order by Id");

        return eatInOrder != null
                ? new ResponseEntity<>(eatInOrder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/table/{tableId}")
    public ResponseEntity<EatInOrder> createOrderForTable(@PathVariable String tableId, @RequestBody EatInOrder eatInOrder) {
        TableRestaurant table = tableRestaurantService.getTableById(tableId);
        if (table == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eatInOrder.setTableRestaurant(table);
        EatInOrder createdEatInOrder = eatInOrderService.createOrder(eatInOrder);
        HttpHeaders headers = getCommonHeaders("Create a new eat-in order for a specific table");

        return createdEatInOrder != null
                ? new ResponseEntity<>(createdEatInOrder, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/table/{tableId}")
    public ResponseEntity<List<EatInOrder>> getOrdersForTable(@PathVariable String tableId) {
        List<EatInOrder> orders = eatInOrderService.getOrdersByTableId(tableId);
        HttpHeaders headers = getCommonHeaders("Get all eat-in orders for a specific table");

        return orders != null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{orderId}/table/{tableId}")
    public ResponseEntity<EatInOrder> updateOrderTable(@PathVariable String orderId, @PathVariable String tableId) {
        EatInOrder updatedOrder = eatInOrderService.updateOrderTable(orderId, tableId);
        HttpHeaders headers = getCommonHeaders("Update the table for an eat-in order");

        return updatedOrder != null
                ? new ResponseEntity<>(updatedOrder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }


    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("order-count", String.valueOf(eatInOrderService.countOrders()));
        headers.add("object", "eat-in-orders");
        return headers;
    }
}
