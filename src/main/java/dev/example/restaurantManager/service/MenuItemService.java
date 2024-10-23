package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItemRestaurant;

import java.util.List;

public interface MenuItemService {

    List<MenuItemRestaurant> getAllItemMenus();
    MenuItemRestaurant getItemMenuById(String id);
    MenuItemRestaurant createItemMenu(MenuItemRestaurant menuItemRestaurant);
    MenuItemRestaurant updateItemMenu(String id, MenuItemRestaurant menuItemRestaurant);
    boolean deleteItemMenu(String id);
    long countItemMenus();
}
