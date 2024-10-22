package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;

import java.util.List;

public interface EatInOrderService {
    List<EatInOrderRestaurant> getAllEatInOrders();

    EatInOrderRestaurant getEatInOrderById(String id);

    EatInOrderRestaurant createEatInOrder(EatInOrderRestaurant eatInOrder);

    EatInOrderRestaurant updateEatInOrder(String id, EatInOrderRestaurant eatInOrder);

    boolean deleteEatInOrder(String id);

    long countEatInOrders();
}
