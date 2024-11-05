package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.MenuItem;

import java.util.List;

public interface MenuItemService {

    List<MenuItem> getAllMenuItems();
    MenuItem createMenuItem(MenuItem menuItem);
    MenuItem getMenuItemByID(String id);
    MenuItem updateMenuItem(String id, MenuItem menuItemDetails);
    boolean deleteMenuItem(String id);
    long countMenuItems();
    List<MenuItem> getMenuItemsByMenuId(String menuId);

}