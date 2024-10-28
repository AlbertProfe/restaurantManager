package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuRestaurantService {
    MenuRestaurant createMenu(MenuRestaurant menuRestaurant);

    Optional<MenuRestaurant> getMenuById(String id);

    List<MenuRestaurant> getAllMenus();

    MenuRestaurant updateMenu(MenuRestaurant menuRestaurant);

    void deleteMenu(String id);

    void addMenuItemToMenu(String menuId, MenuItem menuItem);

    List<MenuItem> getMenuItems(String menuId);
}
