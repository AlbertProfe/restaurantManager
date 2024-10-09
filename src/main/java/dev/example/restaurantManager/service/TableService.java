package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Table;

import java.util.List;

public interface TableService {
    List<Table> getAllTables();
    Table createTable(Table table);
    Table updateTable(String id,Table tableDetails);
    void deleteTable(String id);
    Table getTableById(String id);
}
