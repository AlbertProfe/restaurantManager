package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getAllMenus();
    Menu createMenu(Menu menu);
    Menu getMenuById(String id);
    Menu updateMenu(String id,Menu MenuDetails);
    boolean deleteMenu(String id);
    long countMenus();
}
