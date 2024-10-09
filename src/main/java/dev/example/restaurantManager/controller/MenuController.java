package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/menu")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/allMenus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @PostMapping
    Menu createMenu(@RequestBody Menu menu){
        return menuService.createMenu(menu);
    }

    @PutMapping("/{id}")
    Menu updateMenu(@PathVariable String id,Menu menuDetails){
        return menuService.updateMenu(id,menuDetails);
    }

    @DeleteMapping("/{id}")
    void deleteMenu(@PathVariable String id){
        menuService.deleteMenu(id);
    }

    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable String id){
        return menuService.getMenuById(id);
    }

}
