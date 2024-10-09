package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Table;
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
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public Table createTable(Table table) {
        table.setId(UUID.randomUUID().toString());
        return tableRepository.save(table);
    }

    @Override
    public Table getTableById(String id) {
        return tableRepository.findById(id).orElse(null);
    }

    @Override
    public Table updateTable(String id, Table tableDetails) {
        Table table = tableRepository.findById(id).orElse(null);
        if (table != null) {
            if (tableDetails.getName() != null) {
                table.setName(tableDetails.getName());
            }
            if (tableDetails.getQty() != null) {
                table.setQty(tableDetails.getQty());
            }
            if (tableDetails.isBusy() != null) {
                table.setBusy(tableDetails.isBusy());
            }
            // Update other fields as necessary
            return tableRepository.save(table);
        }
        return null;
    }

    @Override
    public boolean deleteTable(String id) {
        tableRepository.deleteById(id);
        Optional<Table> table = tableRepository.findById(id);
        return table.isEmpty();
    }

    @Override
    public long countTables() {
        return tableRepository.count();
    }
}
