package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderRestaurant;

import java.util.List;

public interface OrderService {
    List<OrderRestaurant> getAllOrders();
    OrderRestaurant createOrder(OrderRestaurant order);
    OrderRestaurant getOrderById(String id);
    OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails);
    boolean deleteOrder(String id);
    long countOrders();


    OrderRestaurant addMenus(String idOrder, List<MenuRestaurant> menus);
    OrderRestaurant deleteMenus(String idOrder, List<MenuRestaurant> menus);
}
