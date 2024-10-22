package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    TableRepository tableRepository;

    @Override
    public TableRestaurant getTableById(String id) {
        Optional<TableRestaurant> table = tableRepository.findById(id);
        if (table.isPresent()) {
            return table.get();
        } else {
            return null;
        }
    }

    @Override
    public List<TableRestaurant> getAllTables() {
        List<TableRestaurant> tables = tableRepository.findAll();
        return tables;
    }

    @Override
    public TableRestaurant createTable(TableRestaurant table) {
        Optional<TableRestaurant> tableOptional = tableRepository.findById(table.getId());
        if (tableOptional.isPresent()) {
            return null;
        } else {
            tableRepository.save(table);
            return table;
        }
    }
    @Override
    public TableRestaurant updateTable(String id, TableRestaurant tableDetails) {
        Optional<TableRestaurant> tableOptional = tableRepository.findById(id);
        if (tableOptional.isPresent()) {
            TableRestaurant table = tableOptional.get();
            table.setName(tableDetails.getName());
            table.setDescription(tableDetails.getDescription());
            table.setQty(tableDetails.getQty());
            table.setBusy(tableDetails.isBusy());
            tableRepository.save(table);
            return table;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteTable(String id) {
        Optional<TableRestaurant> table = tableRepository.findById(id);
        if (table.isPresent()) {
            tableRepository.delete(table.get());
            return true;
        } else {
            return false;
        }
    }
    @Override
    public long countTables() {
        return tableRepository.count();
    }
}
