package dev.example.restaurantManager.controller;


import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.service.EatInService;
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
    private EatInService eatInService;

    //manage request by ResponseEntity with all EatInOrder
    @GetMapping("/alleatinorders")

    public ResponseEntity<List<EatInOrderRestaurant>> getAllEatInOrders(){
        List<EatInOrderRestaurant> eatinorders = eatInService.getAllEatInOrders();
        HttpHeaders headers = getCommonHeaders("Get alls Eat In Restaurant Orders");
        return eatinorders !=null && !eatinorders.isEmpty()
                ? new ResponseEntity<>(eatinorders,headers, HttpStatus.OK)
                : new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
    }

    @PostMapping

    public ResponseEntity<EatInOrderRestaurant> createEatInOrder(@RequestBody EatInOrderRestaurant eatinorder) {
        EatInOrderRestaurant createdEatInOrder = eatInService.createEatInOrders(eatinorder);
        HttpHeaders headers = getCommonHeaders("Create Eat In Order Restaurant");
        return createdEatInOrder !=null
                ?new ResponseEntity<>(createdEatInOrder, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")

    public ResponseEntity<EatInOrderRestaurant> updateEatInOrder(@PathVariable String id, @RequestBody EatInOrderRestaurant eatInOrderDetails){
        EatInOrderRestaurant updatedEatInOrder = eatInService.updateEatInOrders(id, eatInOrderDetails);
        HttpHeaders headers = getCommonHeaders("Update Eat In Restaurant Order");
        return updatedEatInOrder != null
                ? new ResponseEntity<>(updatedEatInOrder,headers,HttpStatus.OK)
                : new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEatInOrder(@PathVariable String id){
        boolean deleted = eatInService.deleteEatInOrders(id);
        HttpHeaders headers = getCommonHeaders("Delete Eat In Order Restaurant");
        return deleted
                ?new ResponseEntity<>(headers,HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);

    }

    @GetMapping("{id}")
    public ResponseEntity<EatInOrderRestaurant> getEatInOrderById(@PathVariable String id){
        EatInOrderRestaurant eatinorder = eatInService.getEatInOrdersById(id);
        HttpHeaders headers = getCommonHeaders("Get a Eat In Order by ID");
        return eatinorder !=null
                ? new ResponseEntity<>(eatinorder, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);

    }




    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("eatInOrders-count",String.valueOf(eatInService.countEatInOrders()));
        headers.add("object", "EatInOrders");
        return headers;
    }

}
