package dev.example.restaurantManager.controller;


import dev.example.restaurantManager.model.RestaurantTable;
import dev.example.restaurantManager.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("api/v1/table")
@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    //Get all restaurant tables
    @GetMapping("/allTables")
    public ResponseEntity<List<RestaurantTable>>getAllTables(){
        List<RestaurantTable> tables = tableService.getAllTables();
        HttpHeaders headers = getCommonHeaders("Get all tables");
        return tables != null && !tables.isEmpty()
                ? new ResponseEntity<>(tables, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    //create a new table
    @PostMapping
    public ResponseEntity<RestaurantTable> createTable(@RequestBody RestaurantTable table){
        RestaurantTable createdTable = tableService.createTable(table);
        HttpHeaders headers = getCommonHeaders("Create a new table");

        return createdTable !=null
                ? new ResponseEntity<>(createdTable, headers,HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);

    }
    //Update an existing restaurant table by name
    @PutMapping("/{name}")
    public ResponseEntity<RestaurantTable> updateTable(@PathVariable String name, @RequestBody RestaurantTable tableDetails) {
        RestaurantTable updatedTable = tableService.updateTable(name, tableDetails);
        HttpHeaders headers = getCommonHeaders("Update a table");
        return updatedTable != null
                ? new ResponseEntity<>(updatedTable, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
    }

    // Delete a restaurant table by name
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteTable(@PathVariable String name) {
        boolean deleted = tableService.deleteTable(name);
        HttpHeaders headers = getCommonHeaders("Delete a table");
        return deleted
                ? new ResponseEntity<>(headers,HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    // Get a table by name
    @GetMapping("/{name}")
    public ResponseEntity<RestaurantTable> getTableByName(@PathVariable String name){
        RestaurantTable table = tableService.getTableByName(name);
        HttpHeaders headers = getCommonHeaders("Get a table by name");

        return table != null
                ? new ResponseEntity<>(table,headers,HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("tables-count", String.valueOf(tableService.countTables()));
        headers.add("object", "tables");
        return headers;
    }
}

