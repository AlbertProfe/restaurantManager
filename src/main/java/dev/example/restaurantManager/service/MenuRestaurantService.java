package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;

import java.util.List;

public interface MenuRestaurantService {

    List<MenuRestaurant> getAllMenus();
    MenuRestaurant getMenuById(String id);
    MenuRestaurant createMenu(MenuRestaurant menuRestaurant);
    MenuRestaurant updateMenu(String id, MenuRestaurant menuRestaurant);
    boolean deleteMenu(String id);
    long countMenus();
}
