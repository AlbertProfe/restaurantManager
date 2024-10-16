package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {

    @Autowired
    private TableRestaurantRepository TableRestaurantRepository;

    @Override
    public List<TableRestaurant> getAllTables() {
        return TableRestaurantRepository.findAll();
    }

    @Override
    public TableRestaurant createTable(TableRestaurant tableRestaurant) {
        tableRestaurant.setId(UUID.randomUUID().toString());
        return TableRestaurantRepository.save(tableRestaurant);
    }

    @Override
    public TableRestaurant getTableById(String id) {
        return TableRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant updateTable(String id, TableRestaurant tableRestaurantDetails) {
        TableRestaurant tableRestaurant = TableRestaurantRepository.findById(id).orElse(null);
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
            return TableRestaurantRepository.save(tableRestaurant);
        }
        return null;
    }

    @Override
    public boolean deleteTable(String id) {
        TableRestaurantRepository.deleteById(id);
        Optional<TableRestaurant> table = TableRestaurantRepository.findById(id);
        return table.isEmpty();
    }

    @Override
    public long countTables() {
        return TableRestaurantRepository.count();
    }
}

