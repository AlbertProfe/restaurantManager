package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService{


    @Autowired
    private TableRepository tablereposotory;


    @Override
    public List<Table> getAllTables() {
        return List.of();
    }

    @Override
    public Table createTable(Table table) {
        return null;
    }

    @Override
    public Table getTableByName(String name) {
        return null;
    }

    @Override
    public Table getTableByQty(int qty) {
        return null;
    }

    @Override
    public Table updateStatusTable(boolean busy) {
        return null;
    }

    @Override
    public boolean deleteTable(String name) {
        return false;
    }
}
