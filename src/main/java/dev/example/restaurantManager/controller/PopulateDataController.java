package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.utilities.FakeDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/populate")
public class PopulateDataController {

    @Autowired
    private FakeDataLoader dataLoader;

    @PostMapping("/all")
    public ResponseEntity<String> PopulateAllData(){
        dataLoader.createDataAndSave2DBWithoutRelationship();
        dataLoader.createSomeRelations();
        return ResponseEntity.ok("All data populated successfully");
    }

}
