package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/menus")
@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    // manage request by ResponseEntity with all menus
    @GetMapping("/allMenus")
    public ResponseEntity<List<MenuRestaurant>> getAllMenus( ) {
        List<MenuRestaurant> menus = menuService.getAllMenus();
        HttpHeaders headers = getCommonHeaders("Get all menus");

        return menus != null && !menus.isEmpty()
                ? new ResponseEntity<>(menus, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MenuRestaurant> createMenu(@RequestBody MenuRestaurant menu) {
        MenuRestaurant createdMenu = menuService.createMenu(menu);
        HttpHeaders headers = getCommonHeaders("Create a new menu");

        return createdMenu != null
                ? new ResponseEntity<>(createdMenu, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuRestaurant> updateMenu(@PathVariable String id, @RequestBody MenuRestaurant menuDetails) {
        MenuRestaurant updatedMenu = menuService.updateMenu(id, menuDetails);
        HttpHeaders headers = getCommonHeaders("Update a menu");

        return updatedMenu != null
                ? new ResponseEntity<>(updatedMenu, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        menuService.deleteMenu(id);
        HttpHeaders headers = getCommonHeaders("Delete a menu");
        boolean deleted = menuService.getMenuById(id) == null;
        headers.add("deleted", String.valueOf(deleted));

        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuRestaurant> getMenuById(@PathVariable String id) {
        MenuRestaurant menu = menuService.getMenuById(id);
        HttpHeaders headers = getCommonHeaders("Get a menu by Id");

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
        headers.add("menus-count", String.valueOf(menuService.countMenus()));
        headers.add("object", "menus");
        return headers;
    }
    
    
}
