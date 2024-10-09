package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return tableRepository.save(table);
    }

    @Override
    public Table updateTable(String id, Table tableDetails) {
        Table oldTable = tableRepository.findById(id).orElse(null);
        if (oldTable == null){
            return null;
        }

        // TODO logica para validar si se deben aplicar los cambios

        return tableRepository.save(tableDetails);
    }

    @Override
    public void deleteTable(String id) {
        tableRepository.deleteById(id);
    }

    @Override
    public Table getTableById(String id) {
        return tableRepository.findById(id).orElse(null);
    }
}
