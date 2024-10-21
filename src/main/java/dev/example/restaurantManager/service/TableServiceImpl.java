package dev.example.restaurantManager.service;


import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    @Override
    public List<TableRestaurant> getAllTables() { return tableRestaurantRepository.findAll(); }

    @Override
    public TableRestaurant createTable(TableRestaurant table) {
        table.setId(UUID.randomUUID().toString());
        return tableRestaurantRepository.save(table);
    }

    @Override
    public TableRestaurant getTableById(String id) {
        return tableRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public TableRestaurant updateTable(String id, TableRestaurant tableDetails) {
        TableRestaurant table = tableRestaurantRepository.findById(id).orElse(null);
        if (table != null) {
            if (tableDetails.getName() != null) {
                table.setName(tableDetails.getName());
            }
            /*if (customerDetails.getEmail() != null) {
                customer.setEmail(customerDetails.getEmail());
            }
            if (customerDetails.getPhoneNumber() != null) {
                customer.setPhoneNumber(customerDetails.getPhoneNumber());
            }*/
            return tableRestaurantRepository.save(table);
        }
        return null;
    }

    @Override
    public boolean deleteTable(String id) {
        tableRestaurantRepository.deleteById(id);
        Optional<TableRestaurant> table = tableRestaurantRepository.findById(id);
        return table.isEmpty()
                ? false : true ;
    }

    @Override
    public long countTables() {
        return tableRestaurantRepository.count();
    }


}
