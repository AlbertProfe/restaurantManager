package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Table;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TableService {

    List<Table> getAllTables();
    Table createTable(Table table);
    Table getTableById(String id);
    Table updateStatusTable(String id, boolean busy, Table tableDetails);
    boolean deleteTable(String id);
    long countTables();


}
