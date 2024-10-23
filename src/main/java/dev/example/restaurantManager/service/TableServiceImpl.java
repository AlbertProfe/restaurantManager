package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService{

    @Autowired
    private TableRestaurantRepository tableRepository;

    @Override
    public List<TableRestaurant> getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public TableRestaurant createTable(TableRestaurant table) {
        return tableRepository.save(table);
    }

    @Override
    public TableRestaurant getTableById(String id) {
        return tableRepository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant updateTable(String id, TableRestaurant tableDetails) {
        TableRestaurant table = getTableById(id);
        if(table== null) {
            return null;
        }
        return tableRepository.save(tableDetails);
    }

    @Override
    public boolean deleteTable(String id) {
        TableRestaurant table = getTableById(id);
        if(table== null) {
            return false;
        }
        tableRepository.deleteById(id);
        return true;
    }

    @Override
    public long countTables() {
        return tableRepository.count();
    }
}
