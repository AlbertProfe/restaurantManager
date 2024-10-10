package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public List<TableRestaurant> getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public TableRestaurant createTable(TableRestaurant tableRestaurant) {
        tableRestaurant.setId(UUID.randomUUID().toString());
        return tableRepository.save(tableRestaurant);
    }

    @Override
    public TableRestaurant getTableById(String id) {
        return tableRepository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant updateTable(String id, TableRestaurant tableRestaurantDetails) {
        TableRestaurant tableRestaurant = tableRepository.findById(id).orElse(null);
        if (tableRestaurant != null) {
            if (tableRestaurantDetails.getName() != null) {
                tableRestaurant.setName(tableRestaurantDetails.getName());
            }
            if (tableRestaurantDetails.getQty() != null) {
                tableRestaurant.setQty(tableRestaurantDetails.getQty());
            }
            if (tableRestaurantDetails.getBusy() != null) {
                tableRestaurant.setBusy(tableRestaurantDetails.getBusy());
            }
            return tableRepository.save(tableRestaurant);
        }
        return null;
    }

    @Override
    public boolean deleteTable(String id) {
        tableRepository.deleteById(id);
        Optional<TableRestaurant> table = tableRepository.findById(id);
        return table.isEmpty();
    }

    @Override
    public long countTables() {
        return tableRepository.count();
    }
}
