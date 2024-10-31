package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;

import java.util.List;

public interface OrderMenuQtyService {
    List<OrderMenuQty> getAllMenus();
    OrderMenuQty getMenuById(String id);
    OrderMenuQty createMenu(OrderMenuQty menuRestaurant);
    OrderMenuQty updateMenu(String id, OrderMenuQty menuRestaurant);
    boolean deleteMenu(String id);
    long countMenus();

}
