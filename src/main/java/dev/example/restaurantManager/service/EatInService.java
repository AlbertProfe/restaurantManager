package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.EatInOrderRestaurant;

import java.util.List;

public interface EatInService {

    List<EatInOrderRestaurant> getAllEatInOrders();
    EatInOrderRestaurant createEatInOrders(EatInOrderRestaurant EatInOrderRestaurant);
    EatInOrderRestaurant getEatInOrdersById(String id);
    EatInOrderRestaurant updateEatInOrders(String id,EatInOrderRestaurant eatInOrderDetails);
    boolean deleteEatInOrders(String id);
    long countEatInOrders();
}