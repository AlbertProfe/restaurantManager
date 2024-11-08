package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuItem.Dessert;
import dev.example.restaurantManager.service.DessertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dessert")
public class DessertController {

    @Autowired
    private DessertService dessertService;

    @GetMapping
    public ResponseEntity<List<Dessert>> getAllDesserts() {
        List<Dessert> desserts = dessertService.getAllDesserts();
        return new ResponseEntity<>(desserts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dessert> createDessert(@RequestBody Dessert dessert) {
        Dessert newDessert = dessertService.createDessert(dessert);
        return new ResponseEntity<>(newDessert, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dessert> getDessertById(@PathVariable String id) {
        Dessert dessert = dessertService.getDessertById(id);
        if (dessert != null) {
            return new ResponseEntity<>(dessert, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dessert> updateDessert(@PathVariable String id, @RequestBody Dessert dessertDetails) {
        Dessert updatedDessert = dessertService.updateDessert(id, dessertDetails);
        if (updatedDessert != null) {
            return new ResponseEntity<>(updatedDessert, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDessert(@PathVariable String id) {
        boolean deleted = dessertService.deleteDessert(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countDesserts() {
        long count = dessertService.countDesserts();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}