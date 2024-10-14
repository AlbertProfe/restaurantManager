package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Table;
import java.util.List;
public interface TableService {

    List<Table> getAllTables();
    Table createTable(Table table);
    Table getTableByName(String name);
    Table getTableByQty(int qty);
    Table updateStatusTable(boolean busy);
    boolean deleteTable(String name);


}
