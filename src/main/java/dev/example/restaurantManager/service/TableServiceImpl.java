package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.RestaurantTable;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;
    @Override
    public List<RestaurantTable> getAllTables(){return tableRepository.findAll();}
    @Override
    public RestaurantTable createTable(RestaurantTable table){
        if(table.getName() == null || table.getName().isEmpty()){
            table.setName("Default Table name");
        }
        return tableRepository.save(table);
    }
    @Override
    public RestaurantTable getTableByName(String name) {
        return tableRepository.findByName(name).orElse(null);

    }
    @Override
    public RestaurantTable updateTable(String name, RestaurantTable tableDetails){
        RestaurantTable table = tableRepository.findByName(name).orElse(null);
        if (table == null){
            return null;
        }
        if(tableDetails.getName()!=null) {
            table.setName(tableDetails.getName());
        }

        if(tableDetails.getDescription()!=null) {
            table.setDescription(tableDetails.getDescription());
        }
        if(tableDetails.getQty()>0) {
                table.setQty(tableDetails.getQty());
        }

        table.setBusy(tableDetails.isBusy());

        return tableRepository.save(table);

    }

    @Override
    public boolean deleteTable (String name){

        RestaurantTable table = tableRepository.findByName(name).orElse(null);
        if(table == null){
            return false;
        }
        tableRepository.delete(table);
        return true;
    }

    @Override
    public long countTables(){return tableRepository.count();}


}

