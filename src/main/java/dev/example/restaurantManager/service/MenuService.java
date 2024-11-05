package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;

import java.util.List;

public interface MenuService {
    List<MenuRestaurant> getAllMenus();
    long countMenus();
}
