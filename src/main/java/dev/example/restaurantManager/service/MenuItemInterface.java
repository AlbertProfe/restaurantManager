package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;

import java.util.List;

public interface MenuItemInterface {

    List<MenuItem> getAllItems();
    MenuItem createItem(MenuItem item);
    MenuItem getItemById(String id);
    MenuItem updateItem(String id,MenuItem itemDetails);
    void deleteItem(String id);
    long countItems();

}
