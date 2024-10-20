package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import java.util.List;

public interface TableRestaurantService {

    List<TableRestaurant> getAllTables();
    TableRestaurant createTableRestaurant(TableRestaurant tableRestaurant);
    TableRestaurant getTableRestaurantById(String id);
    TableRestaurant updateTableRestaurant(String id, TableRestaurant tableRestaurantDetails);
    boolean deleteTableRestaurant(String id);
    long countTableRestaurant();

}
