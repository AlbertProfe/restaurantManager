package dev.example.restaurantManager.controller;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;


    @GetMapping("/allMenus")
    public ResponseEntity<List<MenuRestaurant>> getAllMenus( ) {
        List<MenuRestaurant> menus = menuService.getAllMenus();
        HttpHeaders headers = getCommonHeaders("Get all menus");

        return menus != null && !menus.isEmpty()
                ? new ResponseEntity<>(menus, headers, HttpStatus.OK)
                : new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    private HttpHeaders getCommonHeaders(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("desc", description);
        headers.add("content-type", "application/json");
        headers.add("date", new Date().toString());
        headers.add("server", "H2 Database");
        headers.add("version", "1.0.0");
        headers.add("menu-count", String.valueOf(menuService.countMenus()));
        headers.add("object", "menus");
        return headers;
    }

}
