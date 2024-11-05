package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;

import java.util.List;

public interface TableRestaurantService {

    List<TableRestaurant> getAllTables();
    TableRestaurant createTable(TableRestaurant table);
    TableRestaurant getTableByName (String name);
    TableRestaurant updateTable (String name, TableRestaurant tableDetails);
    boolean deleteTable(String name);
    long countTables();
}