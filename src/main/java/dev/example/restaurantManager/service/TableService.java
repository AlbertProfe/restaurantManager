package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;

import java.util.List;

public interface TableService {
    List<TableRestaurant> getAllTables();
    TableRestaurant createTable(TableRestaurant table);
    TableRestaurant getTableById(String id);
    TableRestaurant updateTable(String id, TableRestaurant tableDetails);
    boolean deleteTable(String id);
    long countTables();
}
