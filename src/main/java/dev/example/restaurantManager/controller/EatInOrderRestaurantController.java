package dev.example.restaurantManager.controller;


import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.EatInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/eatinorder")
public class EatInOrderRestaurantController {

    @Autowired
    private EatInOrderService eatInOrderService;

    @RequestMapping("/allEatInOrders")
    public ResponseEntity<List<EatInOrderRestaurant>> getAllItInOrderRestaurant(){
        List<EatInOrderRestaurant> eatInOrderRestaurants = eatInOrderService.getAllEatInOrderRestaurant();
        HttpHeaders headers = getCommonHeaders("Get all tables");

        return eatInOrderRestaurants != null && !eatInOrderRestaurants.isEmpty()
                ? new ResponseEntity<>(eatInOrderRestaurants, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<EatInOrderRestaurant> createMenu(@RequestBody EatInOrderRestaurant eatInOrderRestaurant) {
        EatInOrderRestaurant createdTable = eatInOrderService.createEatInOrder(eatInOrderRestaurant);
        HttpHeaders headers = getCommonHeaders("Create a new table");

        return createdTable != null
                ? new ResponseEntity<>(createdTable, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<EatInOrderRestaurant> updateTable(@PathVariable String id, @RequestBody EatInOrderRestaurant eatInOrderRestaurantDetails) {
        EatInOrderRestaurant updatedEatInOrder = eatInOrderService.updateEatInOrder(id, eatInOrderRestaurantDetails);
        HttpHeaders headers = getCommonHeaders("Update a eatInOrder");

        return updatedEatInOrder != null
                ? new ResponseEntity<>(updatedEatInOrder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEatInOrder(@PathVariable String id) {
        boolean deleted = eatInOrderService.deleteEatInOrder(id);
        HttpHeaders headers = getCommonHeaders("Delete a EatInOrder");

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EatInOrderRestaurant> getEatInOrder(@PathVariable String id) {
        EatInOrderRestaurant eatInOrder = eatInOrderService.getEatInOrderById(id);
        HttpHeaders headers = getCommonHeaders("Get a eatInOrder");

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
        headers.add("menu-count", String.valueOf(eatInOrderService.countEatInOrder()));
        headers.add("object", "eatIntOrderRestaurant");
        return headers;
    }


}
