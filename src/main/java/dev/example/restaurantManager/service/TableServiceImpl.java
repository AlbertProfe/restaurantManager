package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TableServiceImpl implements TableService{


    @Autowired
    private TableRepository tableRepository;

    @Override
    public List<TableRestaurant> getAllTables() {
        return tableRepository.findAll();
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
    public TableRestaurant getTableById(String id) {
         return tableRepository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant updateTable(String id, TableRestaurant tableRestaurantDetails) {
        TableRestaurant table = tableRepository.findById(id).orElse(null);
        if (table != null){
            if (tableRestaurantDetails.getName() != null){
                table.setName(tableRestaurantDetails.getName());
            }
            if (tableRestaurantDetails.getQty() != 0){
                table.setQty(tableRestaurantDetails.getQty());
            }
            if (!tableRestaurantDetails.isBusy()){
                table.setBusy(tableRestaurantDetails.isBusy());
            }
            return tableRepository.save(table);

        }
        return null;
    }

    @Override
    public boolean deleteTable(String id) {
        tableRepository.deleteById(id);
        Optional<TableRestaurant> table = tableRepository.findById(id);
        return table.isPresent();
    }

    @Override
    public long countTables() {return tableRepository.count();
    }
}
