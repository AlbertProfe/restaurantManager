package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {

    @Autowired
    private TableRestaurantRepository tableRepository;
    @Override
    public List<TableRestaurant> getAllTables(){return tableRepository.findAll();}
    @Override
    public TableRestaurant createTable(TableRestaurant table){
        if(table.getName() == null || table.getName().isEmpty()){
            table.setName("Default Table name");
        }
        return tableRepository.save(table);
    }
    @Override
    public TableRestaurant getTableByName(String name) {
        return tableRepository.findByName(name).orElse(null);

    }
    @Override
    public TableRestaurant updateTable(String name, TableRestaurant tableDetails){
        TableRestaurant table = tableRepository.findByName(name).orElse(null);
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

        TableRestaurant table = tableRepository.findByName(name).orElse(null);
        if(table == null){
            return false;
        }
        tableRepository.delete(table);
        return true;
    }

    @Override
    public long countTables(){return tableRepository.count();}


}