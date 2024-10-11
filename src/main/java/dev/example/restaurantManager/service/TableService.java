package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface TableService {

    List<TableRestaurant> getAllTables();

    TableRestaurant getTableById(String id);

    TableRestaurant createTable(TableRestaurant table);

    TableRestaurant updateTable(String id, TableRestaurant tableDetails);

    boolean deleteTable(String id);
    long countTables();
}
