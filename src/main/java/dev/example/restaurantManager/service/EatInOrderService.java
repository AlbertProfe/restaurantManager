package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;

import java.util.List;

public interface EatInOrderService {
    List<EatInOrderRestaurant> getAllEatInOrders();
    EatInOrderRestaurant createEatInOrder(EatInOrderRestaurant eatInOrder);
    EatInOrderRestaurant getEatInOrderById(String id);
    EatInOrderRestaurant updateEatInOrder(String id, EatInOrderRestaurant eatInOrderDetails);
    boolean deleteEatInOrder(String id);
    long countEatInOrders();
}
