package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.repository.ITableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class TableService implements IService<Table>{

    @Autowired
    private ITableRepository tableRepository;

    @Override
    public List<Table> getAllElements() {
        return tableRepository.findAll();
    }

    @Override
    public Table createElement(Table element) {
        return tableRepository.save(element);
    }

    @Override
    public Table getElementById(String id) {
        return tableRepository.findById(id).orElse(null);
    }

    @Override
    public Table updateElement(String id, Table eDetails) {
        boolean isTrue = false;
        Table tableElement = tableRepository.findById(id).orElse(null);
        if(tableElement != null){
            Field[] fields = tableElement.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(tableElement) != field.get(eDetails)){
                        isTrue = true;
                        field.set(tableElement,field.get(eDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            tableRepository.save(tableElement);
        }

        return isTrue ? tableElement : eDetails;
    }

    @Override
    public boolean deleteElement(String id) {
        tableRepository.deleteById(id);
        Optional<Table> table = tableRepository.findById(id);
        return table.isEmpty()
                ? false : true ;
    }

    @Override
    public long countElements() {
        return 0;
    }
}
