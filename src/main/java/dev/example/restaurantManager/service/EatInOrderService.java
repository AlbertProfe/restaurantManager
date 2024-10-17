package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrder;

import java.util.List;

public interface EatInOrderService {
    List<EatInOrder> getAllOrders();
    EatInOrder createOrder(EatInOrder eatInOrderRestaurant);
    EatInOrder getOrderById(String id);
    EatInOrder updateOrder(String id, EatInOrder eatInOrderRestaurantDetails);
    boolean deleteOrder(String id);
    long countOrders();
    List<EatInOrder> getOrdersByTableId(String tableId);
    EatInOrder updateOrderTable(String orderId, String tableId);
}
