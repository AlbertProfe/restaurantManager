package dev.example.restaurantManager.controller;



import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/menuItems")
@RestController
public class MenuItemController {


    @Autowired
    private MenuItemService menuItemService;

    // inject  from application.properties endpoint.url.menuItems
    @Value("${endpoint.url.menuItems}")
    private String endpointUrlMenuItems;

    @GetMapping("/show-endpoint")
    public String showEndpointMenuItems() {

        return "The menuItems endpoint URL is: " + endpointUrlMenuItems;
    }

    // manage request by ResponseEntity with all customers
    @GetMapping("/allCustomers")
    public ResponseEntity<List<MenuItem>> getAllMenuItems( ) {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        HttpHeaders headers = getCommonHeaders("Get all customers");

        return menuItems != null && !menuItems.isEmpty()
                ? new ResponseEntity<>(menuItems, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
        HttpHeaders headers = getCommonHeaders("Create a new customer");

        return createdMenuItem != null
                ? new ResponseEntity<>(createdMenuItem, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String id, @RequestBody MenuItem menuItemDetails) {
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(id, menuItemDetails);
        HttpHeaders headers = getCommonHeaders("Update a menuItem");

        return updatedMenuItem != null
                ? new ResponseEntity<>(updatedMenuItem, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String id) {
        boolean deleted = menuItemService.deleteMenuItem(id);
        HttpHeaders headers = getCommonHeaders("Delete a menuItem");
        headers.add("deleted", String.valueOf(deleted));


        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable String id) {
        MenuItem menuItem = menuItemService.getMenuItemById(id);
        HttpHeaders headers = getCommonHeaders("Get a menuItem by Id");

        return menuItem != null
                ? new ResponseEntity<>(menuItem, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }


    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("menuItem-count", String.valueOf(menuItemService.countMenuItems()));
        headers.add("object", "menuItems");
        return headers;
    }
}
