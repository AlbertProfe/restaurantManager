package dev.example.restaurantManager.service;


import dev.example.restaurantManager.model.TableRest;

import java.util.List;

public interface TableService {
    List<TableRest> getAllTables();
    TableRest createTable(TableRest table);
    TableRest getTableById(String id);
    TableRest updateTable(String id, TableRest tableDetails);
    boolean deleteTable(String id);
    long countTables();
}

