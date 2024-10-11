package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.service.CustomerService;
import dev.example.restaurantManager.service.MenuRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/menu")
@RestController
public class MenuController {

    @Autowired
    private MenuRestaurantService menuRestaurantService;
    @GetMapping("/allMenus")
    public ResponseEntity<List<MenuRestaurant>> getAllMenus( ) {
        List<MenuRestaurant> menuRestaurants = menuRestaurantService.getAllMenus();
        HttpHeaders headers = getCommonHeaders("Get all menus");

        return menuRestaurants != null && !menuRestaurants.isEmpty()
                ? new ResponseEntity<>(menuRestaurants, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<MenuRestaurant> createMenu(@RequestBody MenuRestaurant menuRestaurant) {
        MenuRestaurant createdMenu = menuRestaurantService.createMenu(menuRestaurant);
        HttpHeaders headers = getCommonHeaders("Create a new menu");

        return createdMenu != null
                ? new ResponseEntity<>(createdMenu, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MenuRestaurant> updateMenu(@PathVariable String id, @RequestBody MenuRestaurant menuRestaurantDetails) {
        MenuRestaurant updatedMenu = menuRestaurantService.updateMenu(id, menuRestaurantDetails);
        HttpHeaders headers = getCommonHeaders("Update a menu");

        return updatedMenu != null
                ? new ResponseEntity<>(updatedMenu, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        boolean deleted = menuRestaurantService.deleteMenu(id);
        HttpHeaders headers = getCommonHeaders("Delete a menu");
        headers.add("deleted", String.valueOf(deleted));

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MenuRestaurant> getMenuById(@PathVariable String id) {
        MenuRestaurant menu = menuRestaurantService.getMenuById(id);
        HttpHeaders headers = getCommonHeaders("Get a menu");

        return menu != null
                ? new ResponseEntity<>(menu, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("menu-count", String.valueOf(menuRestaurantService.countMenus()));
        headers.add("object", "customers");
        return headers;
    }
}
