package dev.example.restaurantManager.controller;


import dev.example.restaurantManager.model.MenuItemRestaurant;
import dev.example.restaurantManager.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/allMenuItems")
    public ResponseEntity<List<MenuItemRestaurant>> getAllMenuItems() {
        List<MenuItemRestaurant> menuItems = menuItemService.getAllItemMenus();
        HttpHeaders headers = getCommonHeaders("Get all menu items");
        return menuItems != null && !menuItems.isEmpty()
                ? new ResponseEntity<>(menuItems, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addMenuItem")
    public ResponseEntity<MenuItemRestaurant> createMenuItem(@RequestBody MenuItemRestaurant menuItem) {
        MenuItemRestaurant createdMenuItem = menuItemService.createItemMenu(menuItem);
        HttpHeaders headers = getCommonHeaders("Create a new menu item");
        return createdMenuItem != null
                ? new ResponseEntity<>(createdMenuItem, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateMenuItem/{id}")
    public ResponseEntity<MenuItemRestaurant> updateMenuItem(@PathVariable String id, @RequestBody MenuItemRestaurant menuItemDetails) {
        MenuItemRestaurant updatedMenuItem = menuItemService.updateItemMenu(id, menuItemDetails);
        HttpHeaders headers = getCommonHeaders("Update a menu item");
        return updatedMenuItem != null
                ? new ResponseEntity<>(updatedMenuItem, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteMenuItem/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String id) {
        boolean deleted = menuItemService.deleteItemMenu(id);
        HttpHeaders headers = getCommonHeaders("Delete a menu item");
        headers.add("deleted", String.valueOf(deleted));
        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getMenuItem/{id}")
    public ResponseEntity<MenuItemRestaurant> getMenuItemById(@PathVariable String id) {
        MenuItemRestaurant menuItem = menuItemService.getItemMenuById(id);
        HttpHeaders headers = getCommonHeaders("Get a menu item by Id");
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
        headers.add("customer-count", String.valueOf(menuItemService.countItemMenus()));
        headers.add("object", "customers");
        return headers;
    }
}
