package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemService {
    MenuItem createMenuItem(MenuItem menuItem);

    Optional<MenuItem> getMenuItemById(String id);

    List<MenuItem> getAllMenuItems();

    MenuItem updateMenuItem(MenuItem menuItem);

    void deleteMenuItem(String id);
}
