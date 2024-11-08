package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Dessert;
import dev.example.restaurantManager.model.MainCourse;
import dev.example.restaurantManager.model.MenuItem;
import java.util.List;

public interface MenuItemService {
    List<MenuItem> getAllMenuItems();
    MenuItem createMenuItem(MenuItem menuItem);
    MenuItem getMenuItemById(String id);
    MenuItem updateMenuItem(String id, MenuItem menuItemDetails, Dessert dessertDetails, MainCourse mainCourseDetails);
    boolean deleteMenuItem(String id);
    long countMenuItems();
}