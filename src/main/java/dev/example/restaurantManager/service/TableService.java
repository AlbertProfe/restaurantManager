package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.RestaurantTable;

import java.util.List;

public interface TableService {
    List<RestaurantTable> getAllTables();
    RestaurantTable createTable(RestaurantTable table);
    RestaurantTable getTableByName (String name);
    RestaurantTable updateTable (String name, RestaurantTable tableDetails);
    boolean deleteTable(String name);
    long countTables();
}
