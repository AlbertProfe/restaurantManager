package dev.example.restaurantManager.service;


import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.TableRest;
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
    public List<TableRest> getAllTables() { return tableRepository.findAll(); }

    @Override
    public TableRest createTable(TableRest table) {
        table.setId(UUID.randomUUID().toString());
        return tableRepository.save(table);
    }

    @Override
    public TableRest getTableById(String id) {
        return tableRepository.findById(id).orElse(null);
    }

    @Override
    public TableRest updateTable(String id, TableRest tableDetails) {
        TableRest table = tableRepository.findById(id).orElse(null);
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
            return tableRepository.save(table);
        }
        return null;
    }

    @Override
    public boolean deleteTable(String id) {
        tableRepository.deleteById(id);
        Optional<TableRest> table = tableRepository.findById(id);
        return table.isEmpty()
                ? false : true ;
    }

    @Override
    public long countTables() {
        return tableRepository.count();
    }


}