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

@RestController
@RequestMapping("/api/v1/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    // Obtener todos los MenuItems
    @GetMapping("/all-menu-items")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        HttpHeaders headers = getCommonHeaders("Get alls menus");
        return menuItems != null && !menuItems.isEmpty()
                ? new ResponseEntity<>(menuItems,headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    // Crear un nuevo MenuItem
    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
        HttpHeaders headers = getCommonHeaders("Create a new menu-item");
        return createdMenuItem != null
                ? new ResponseEntity<>(createdMenuItem, headers, HttpStatus.CREATED)
                : new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);

    }

    // Actualizar un MenuItem existente
    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String id, @RequestBody MenuItem menuItemDetails) {
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(id, menuItemDetails);
        HttpHeaders headers = getCommonHeaders("Update menu-item");
        return updatedMenuItem != null
                ? new ResponseEntity<>(updatedMenuItem,headers,HttpStatus.OK)
                : new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
    }

    // Eliminar un MenuItem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String id) {
        boolean deleted = menuItemService.deleteMenuItem(id);
        HttpHeaders headers = getCommonHeaders("Delete a menu-item");
        return deleted
                ? new ResponseEntity<>(headers,HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    // encuentra un menu-item por la id
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> GetMenuItemById(@PathVariable String id){
        MenuItem menuItem = menuItemService.getMenuItemByID(id);
        HttpHeaders headers = getCommonHeaders("Get a menu-item by Id");
        return  menuItem !=null
                ?new ResponseEntity<>(menuItem, headers, HttpStatus.OK)
                :new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    // Obtener todos los MenuItems de un Menu específico
    @GetMapping("/menu/{menuId}/items")
    public ResponseEntity<List<MenuItem>> getMenuItemsByMenuId(@PathVariable String menuId) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByMenuId(menuId);
        HttpHeaders headers = getCommonHeaders("Get all menu-items for a specific menu");
        return menuItems != null && !menuItems.isEmpty()
                ? new ResponseEntity<>(menuItems, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("menu_items-count", String.valueOf(menuItemService.countMenuItems()));
        headers.add("object", "menu-items");
        return headers;
    }
}