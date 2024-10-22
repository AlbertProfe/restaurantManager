package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;

import java.util.List;

public interface MenuRestaurantService {

    List<MenuRestaurant> getAllMenus();
    MenuRestaurant createMenu(MenuRestaurant menuRestaurant);
    MenuRestaurant getMenuById(String id);
    MenuRestaurant updateMenu(String id, MenuRestaurant menuRestaurantDetails);
    boolean deleteMenu(String id);
    long countMenus();
}
