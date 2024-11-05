package dev.example.restaurantManager.controller;


import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.service.OrderMenuQtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/orderMenuQty")
@RestController
public class OrderMenuQtyController {

    @Autowired
    private OrderMenuQtyService orderMenuQtyService;

    @GetMapping("allOrderMenuQty")
    public ResponseEntity<List<OrderMenuQty>> getAllOrderMenuQty(){
        List<OrderMenuQty> orderMenuQtyList = orderMenuQtyService.getAllOrderMenuQts();
        HttpHeaders headers = getCommonHeaders("Get all OrderMenuQty entries");
        return orderMenuQtyList !=null && !orderMenuQtyList.isEmpty()
                ?new ResponseEntity<>(orderMenuQtyList,headers, HttpStatus.OK)
                :new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<OrderMenuQty> createOrderMenuQty(@RequestBody OrderMenuQty orderMenuQty){
        OrderMenuQty createdOrderMenuQty = orderMenuQtyService.createOrderMenuQty(orderMenuQty);
        HttpHeaders headers = getCommonHeaders("Create a new OrderMenuQty");
        return createdOrderMenuQty !=null
                ?new ResponseEntity<>(createdOrderMenuQty,headers,HttpStatus.CREATED)
                :new ResponseEntity<>(headers,HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderMenuQty> updatedOrderMenuQty(@PathVariable String id, @RequestBody OrderMenuQty orderMenuQtyDetails){
        OrderMenuQty updatedOrderMenuQty = orderMenuQtyService.updateOrderMenuQty(id, orderMenuQtyDetails);
        HttpHeaders headers = getCommonHeaders("Updated a OrderMenuQty");

        return updatedOrderMenuQty !=null
                ?new ResponseEntity<>(updatedOrderMenuQty, headers, HttpStatus.OK)
                :new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderMenuQty> deleteOrderMenuQty(@PathVariable String id){
        boolean deleted = orderMenuQtyService.deleteOrderMenuQty(id);
        HttpHeaders headers = getCommonHeaders("delete an OrderMenuQty");

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderMenuQty> getOrderMenuQtyByID(@PathVariable String id){
        OrderMenuQty orderMenuQty = orderMenuQtyService.getOrderMenuQtyById(id);
        HttpHeaders headers = getCommonHeaders("Get an OrderMenuQty by Id");
        return orderMenuQty !=null
                ? new ResponseEntity<>(orderMenuQty, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);

    }



    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("orderMenuQty-count", String.valueOf(orderMenuQtyService.countOrderMenuQts()));
        headers.add("object", "orderMenuQty");
        return headers;
    }
}