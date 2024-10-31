/*
package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.utilities.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/populate")
public class DataPopulate {

    @Autowired
    private DataLoader dataLoader;

    @PostMapping("/all")
    public ResponseEntity<String> AllDataPopulate() {
        dataLoader.loadAllData();
        return ResponseEntity.ok("All data populated successfully");
    }
}*/
