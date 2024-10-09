package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Table;
import java.util.List;

public interface TableService {
    List<Table> getAllTables();
    Table createTable(Table table);
    Table getTableById(String id);
    Table updateTable(String id, Table tableDetails);
    boolean deleteTable(String id);
    long countTables();
}

