package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/items")
@RestController
public class MenuItemController {

    @Autowired
    MenuItemService itemService;

    // manage request by ResponseEntity with all menus
    @GetMapping("/allItems")
    public ResponseEntity<List<MenuItem>> getAllItems( ) {
        List<MenuItem> menus = itemService.getAllItems();
        HttpHeaders headers = getCommonHeaders("Get all items");

        return menus != null && !menus.isEmpty()
                ? new ResponseEntity<>(menus, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenu(@RequestBody MenuItem item) {
        MenuItem createdItem = itemService.createItem(item);
        HttpHeaders headers = getCommonHeaders("Create a new item");

        return createdItem != null
                ? new ResponseEntity<>(createdItem, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateItem(@PathVariable String id, @RequestBody MenuItem itemDetails) {
        MenuItem updatedItem = itemService.updateItem(id, itemDetails);
        HttpHeaders headers = getCommonHeaders("Update an item");

        return updatedItem != null
                ? new ResponseEntity<>(updatedItem, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        HttpHeaders headers = getCommonHeaders("Delete an item");
        boolean deleted = itemService.getItemById(id) == null;
        headers.add("deleted", String.valueOf(deleted));

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getItemById(@PathVariable String id) {
        MenuItem item = itemService.getItemById(id);
        HttpHeaders headers = getCommonHeaders("Get an item by Id");

        return item != null
                ? new ResponseEntity<>(item, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("items-count", String.valueOf(itemService.countItems()));
        headers.add("object", "items");
        return headers;
    }
    
    
}
