package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAllMenus();
    Menu createMenu(Menu menu);
    Menu updateMenu(String id, Menu menuDetails);
    void deleteMenu(String id);
    Menu getMenuById(String id);
}
