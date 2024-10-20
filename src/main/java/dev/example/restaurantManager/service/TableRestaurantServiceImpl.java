package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TableRestaurantServiceImpl implements TableRestaurantService{
    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    @Override
    public List<TableRestaurant> getAllTables() {
        return tableRestaurantRepository.findAll();
    }

    @Override
    public TableRestaurant createTableRestaurant(TableRestaurant tableRestaurant) {
        tableRestaurant.setId(UUID.randomUUID().toString());
        return tableRestaurantRepository.save(tableRestaurant);
    }

    @Override
    public TableRestaurant getTableRestaurantById(String id) {
        return tableRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant updateTableRestaurant(String id, TableRestaurant tableRestaurantDetails) {
        Optional<TableRestaurant> tableOptional = tableRestaurantRepository.findById(id);
        if (tableOptional.isPresent()) {
            TableRestaurant table = tableOptional.get();
            table.setName(tableRestaurantDetails.getName());
            table.setDescription(tableRestaurantDetails.getDescription());
            table.setQty(tableRestaurantDetails.getQty());
            table.setBusy(tableRestaurantDetails.isBusy());
            tableRestaurantRepository.save(table);
            return table;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteTableRestaurant(String id) {
        tableRestaurantRepository.deleteById(id);
        Optional<TableRestaurant> tableRestaurant = tableRestaurantRepository.findById(id);
        return tableRestaurant.isEmpty()
                ?false
                :true;
    }

    @Override
    public long countTableRestaurant() {
        return tableRestaurantRepository.count();
    }
}
