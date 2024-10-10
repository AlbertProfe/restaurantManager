package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/menu")
@RestController
public class MenuController {
    @Autowired
    private IService<Menu> menuService;

    @GetMapping("/allMenus")
    public List<Menu> getAllMenus() {
        return menuService.getAllElements();
    }

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createElement(menu);
    }

    @PutMapping("/{id}")
    public Menu updateMenu(@PathVariable String id, @RequestBody Menu menuDetails) {
        return menuService.updateElement(id, menuDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable String id) {
        menuService.deleteElement(id);
    }
}
