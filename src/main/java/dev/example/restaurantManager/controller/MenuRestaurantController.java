package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.service.MenuRestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus-restaurant")
public class MenuRestaurantController {
    private final MenuRestaurantService menuRestaurantService;

    public MenuRestaurantController(MenuRestaurantService menuRestaurantService) {
        this.menuRestaurantService = menuRestaurantService;
    }

    @PostMapping
    public ResponseEntity<MenuRestaurant> createMenu(@RequestBody MenuRestaurant menuRestaurant) {
        MenuRestaurant created = menuRestaurantService.createMenu(menuRestaurant);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuRestaurant> getMenu(@PathVariable String id) {
        return menuRestaurantService.getMenuById(id)
                .map(menu -> new ResponseEntity<>(menu, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<MenuRestaurant>> getAllMenus() {
        List<MenuRestaurant> menus = menuRestaurantService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuRestaurant> updateMenu(@PathVariable String id, @RequestBody MenuRestaurant menuRestaurant) {
        menuRestaurant.setId(id);
        MenuRestaurant updated = menuRestaurantService.updateMenu(menuRestaurant);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        menuRestaurantService.deleteMenu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{menuId}/items")
    public ResponseEntity<Void> addMenuItemToMenu(@PathVariable String menuId, @RequestBody MenuItem menuItem) {
        menuRestaurantService.addMenuItemToMenu(menuId, menuItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{menuId}/items")
    public ResponseEntity<List<MenuItem>> getMenuItems(@PathVariable String menuId) {
        List<MenuItem> items = menuRestaurantService.getMenuItems(menuId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
