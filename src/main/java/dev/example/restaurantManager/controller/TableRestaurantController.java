package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.service.TableRestaurantService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

    @RequestMapping("/api/v1/table")
    @RestController
    public class TableRestaurantController {

        @Autowired
        private TableRestaurantService tableRestaurantService;

        @GetMapping("/allTables")
        public ResponseEntity<List<TableRestaurant>> getAllTables() {
            List<TableRestaurant> tableRestaurants = tableRestaurantService.getAllTables();
            HttpHeaders headers = getCommonHeaders("Get all tables");
            return tableRestaurants != null && !tableRestaurants.isEmpty()
                    ? new ResponseEntity<>(tableRestaurants, headers, HttpStatus.OK)
                    : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

        @PostMapping
        public ResponseEntity<TableRestaurant> createTable(@RequestBody TableRestaurant tableRestaurant) {
            TableRestaurant createdTableRestaurant = tableRestaurantService.createTable(tableRestaurant);
            HttpHeaders headers = getCommonHeaders("Create a new table");

            return createdTableRestaurant != null
                    ? new ResponseEntity<>(createdTableRestaurant, headers, HttpStatus.CREATED)
                    : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

        @PutMapping("/{id}")
        public ResponseEntity<TableRestaurant> updateTable(@PathVariable String id, @RequestBody TableRestaurant tableRestaurantDetails) {
            TableRestaurant updatedTableRestaurant = tableRestaurantService.updateTable(id, tableRestaurantDetails);
            HttpHeaders headers = getCommonHeaders("Update a table");

            return updatedTableRestaurant != null
                    ? new ResponseEntity<>(updatedTableRestaurant, headers, HttpStatus.OK)
                    : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTable(@PathVariable String id) {
            boolean deleted = tableRestaurantService.deleteTable(id);
            HttpHeaders headers = getCommonHeaders("Delete a table");
            headers.add("deleted", String.valueOf(deleted));


            return deleted
                    ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

        @GetMapping("/{id}")
        public ResponseEntity<TableRestaurant> getTableById(@PathVariable String id) {
            TableRestaurant tableRestaurant = tableRestaurantService.getTableById(id);
            HttpHeaders headers = getCommonHeaders("Get a customer by Id");

            return tableRestaurant != null
                    ? new ResponseEntity<>(tableRestaurant, headers, HttpStatus.OK)
                    : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

        private HttpHeaders getCommonHeaders(String description) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", description);
            headers.add("content-type", "application/json");
            headers.add("date", new Date().toString());
            headers.add("server", "H2 Database");
            headers.add("version", "1.0.0");
            headers.add("customer-count", String.valueOf(tableRestaurantService.countTables()));
            headers.add("object", "customers");
            return headers;
        }
    }
