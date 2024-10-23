package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.service.EatInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/eatinorder")
@RestController
public class EatInOrderController {

    @Autowired
    private EatInOrderService eatInOrderService;


    // manage request by ResponseEntity with all eatInOrders
    @GetMapping("/allEatInOrders")
    public ResponseEntity<List<EatInOrderRestaurant>> getAllEatInOrders( ) {
        List<EatInOrderRestaurant> eatInOrders = eatInOrderService.getAllEatInOrders();
        HttpHeaders headers = getCommonHeaders("Get all eatInOrders");

        return eatInOrders != null && !eatInOrders.isEmpty()
                ? new ResponseEntity<>(eatInOrders, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EatInOrderRestaurant> createEatInOrder(@RequestBody EatInOrderRestaurant eatInOrder) {
        EatInOrderRestaurant createdEatInOrder = eatInOrderService.createEatInOrder(eatInOrder);
        HttpHeaders headers = getCommonHeaders("Create a new eatInOrder");

        return createdEatInOrder != null
                ? new ResponseEntity<>(createdEatInOrder, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EatInOrderRestaurant> updateEatInOrder(@PathVariable String id, @RequestBody EatInOrderRestaurant eatInOrderDetails) {
        EatInOrderRestaurant updatedEatInOrder = eatInOrderService.updateEatInOrder(id, eatInOrderDetails);
        HttpHeaders headers = getCommonHeaders("Update a eatInOrder");

        return updatedEatInOrder != null
                ? new ResponseEntity<>(updatedEatInOrder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEatInOrder(@PathVariable String id) {
        boolean deleted = eatInOrderService.deleteEatInOrder(id);
        HttpHeaders headers = getCommonHeaders("Delete a eatInOrder");
        headers.add("deleted", String.valueOf(deleted));


        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EatInOrderRestaurant> getEatInOrderById(@PathVariable String id) {
        EatInOrderRestaurant eatInOrder = eatInOrderService.getEatInOrderById(id);
        HttpHeaders headers = getCommonHeaders("Get a eatInOrder by Id");

        return eatInOrder != null
                ? new ResponseEntity<>(eatInOrder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("eatinorders-count", String.valueOf(eatInOrderService.countEatInOrders()));
        headers.add("object", "eatinorder");
        return headers;
    }



}
