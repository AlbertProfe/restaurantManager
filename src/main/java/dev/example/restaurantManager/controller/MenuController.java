package dev.example.restaurantManager.controller;


import dev.example.restaurantManager.model.MenuRestaurant;
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
    private MenuRestaurantService menuService;

    @GetMapping("/allMenus")
    public ResponseEntity<List<MenuRestaurant>> getAllMenus(){
        List<MenuRestaurant> menus = menuService.getAllMenus();
        HttpHeaders headers1 = getCommonHeaders("get all menus");
        return menus !=null && !menus.isEmpty()
                ? new ResponseEntity<>(menus, headers1, HttpStatus.OK)
                : new ResponseEntity<>(headers1, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MenuRestaurant> createMenu(@RequestBody MenuRestaurant menu) {
        MenuRestaurant createdMenu = menuService.createMenu(menu);
        HttpHeaders headers1 = getCommonHeaders("Create new menu");

        return createdMenu !=null
                ? new ResponseEntity<>(createdMenu, headers1,HttpStatus.CREATED)
                : new ResponseEntity<>(headers1,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuRestaurant> updateMenu(@PathVariable String id, @RequestBody MenuRestaurant menuDetails){
        MenuRestaurant updatedMenu = menuService.updateMenu(id, menuDetails);
        HttpHeaders headers = getCommonHeaders("Update menu");


        return updatedMenu !=null
                ? new ResponseEntity<>(updatedMenu,headers,HttpStatus.OK)
                : new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id){
        boolean deleted = menuService.deleteMenu(id);
        HttpHeaders headers = getCommonHeaders("Delete a Menu");
        headers.add("deleted", String.valueOf(deleted));
        return deleted
                ? new ResponseEntity<>(headers, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);


    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuRestaurant> getMenuById(@PathVariable String id) {
        MenuRestaurant menu   = menuService.getMenuById(id);
        HttpHeaders headers = getCommonHeaders("Get a Menu by Id");

        return menu != null
                ? new ResponseEntity<>(menu, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }


    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("desc", description);
        headers1.add("content-type", "application/json");
        headers1.add("date", new Date().toString());
        headers1.add("server", "H2 Database");
        headers1.add("version", "1.0.0");
        headers1.add("menus-count", String.valueOf(menuService.countMenus()));
        headers1.add("object", "menus");
        return headers1;
    }

}