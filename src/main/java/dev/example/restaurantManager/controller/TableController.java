package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/table")
@RestController
public class TableController {
    
    @Autowired
    private TableService tableService;


    // manage request by ResponseEntity with all tables
    @GetMapping("/allTables")
    public ResponseEntity<List<TableRestaurant>> getAllTables( ) {
        List<TableRestaurant> tables = tableService.getAllTables();
        HttpHeaders headers = getCommonHeaders("Get all tables");

        return tables != null && !tables.isEmpty()
                ? new ResponseEntity<>(tables, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TableRestaurant> createTable(@RequestBody TableRestaurant table) {
        TableRestaurant createdTable = tableService.createTable(table);
        HttpHeaders headers = getCommonHeaders("Create a new table");

        return createdTable != null
                ? new ResponseEntity<>(createdTable, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableRestaurant> updateTable(@PathVariable String id, @RequestBody TableRestaurant tableDetails) {
        TableRestaurant updatedTable = tableService.updateTable(id, tableDetails);
        HttpHeaders headers = getCommonHeaders("Update a table");

        return updatedTable != null
                ? new ResponseEntity<>(updatedTable, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable String id) {
        boolean deleted = tableService.deleteTable(id);
        HttpHeaders headers = getCommonHeaders("Delete a table");
        headers.add("deleted", String.valueOf(deleted));


        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableRestaurant> getTableById(@PathVariable String id) {
        TableRestaurant table = tableService.getTableById(id);
        HttpHeaders headers = getCommonHeaders("Get a table by Id");

        return table != null
                ? new ResponseEntity<>(table, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("table-count", String.valueOf(tableService.countTables()));
        headers.add("object", "tables");
        return headers;
    }    
    
}
