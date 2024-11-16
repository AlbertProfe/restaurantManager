package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MainCourse;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.SideCourse;
import dev.example.restaurantManager.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menuitems")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    // Get all MenuItems (including MainCourse and Dessert)

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    // Create a MenuItem (can be a MainCourse, Dessert, or other MenuItem)

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem newMenuItem = menuItemService.createMenuItem(menuItem);
        return new ResponseEntity<>(newMenuItem, HttpStatus.CREATED);
    }
    // Get a MenuItem by ID (including MainCourse and Dessert)

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable String id) {
        MenuItem menuItem = menuItemService.getMenuItemById(id);
        if (menuItem != null) {
            return new ResponseEntity<>(menuItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Update an existing MenuItem

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String id, @RequestBody MenuItem menuItemDetails) {
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(id, menuItemDetails);
        if (updatedMenuItem != null) {
            return new ResponseEntity<>(updatedMenuItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Delete a MenuItem by ID

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String id) {
        boolean deleted = menuItemService.deleteMenuItem(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Get the count of all MenuItems

    @GetMapping("/count")
    public ResponseEntity<Long> countMenuItems() {
        long count = menuItemService.countMenuItems();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Endpoint to create a MainCourse
    @PostMapping("/maincourse")
    public ResponseEntity<MainCourse> createMainCourse(@RequestBody MainCourse mainCourse){
        MainCourse newMainCourse = menuItemService.createMainCourse(mainCourse);
        return new ResponseEntity<>(newMainCourse, HttpStatus.CREATED);
    }

    @PostMapping("/sidecourse")
    public ResponseEntity<SideCourse> createSideCourse (@RequestBody SideCourse sideCourse){
        SideCourse newSideCourse = menuItemService.createSideCourse(sideCourse);
        return new ResponseEntity<>(newSideCourse,HttpStatus.CREATED);
    }

}