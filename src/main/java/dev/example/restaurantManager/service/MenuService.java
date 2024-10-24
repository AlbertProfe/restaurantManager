package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;

import java.util.List;

public interface MenuService {
    List<MenuRestaurant> getAllMenus();
    MenuRestaurant createMenu(MenuRestaurant menu);
    MenuRestaurant getMenuById(String id);
    MenuRestaurant updateMenu(String id,MenuRestaurant menuDetails);
    void deleteMenu(String id);
    long countMenus();
}
