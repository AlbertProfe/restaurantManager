package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.repository.IMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService implements IService<Menu>{

    @Autowired
    private IMenuRepository menuRepository;

    @Override
    public List<Menu> getAllElements() {
        return menuRepository.findAll();
    }

    @Override
    public Menu createElement(Menu element) {
        return menuRepository.save(element);
    }

    @Override
    public Menu getElementById(String id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public Menu updateElement(String id, Menu eDetails) {
        boolean isTrue = false;
        Menu menuElement = menuRepository.findById(id).orElse(null);
        if(menuElement != null){
            Field[] fields = menuElement.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if(field.get(menuElement) != field.get(eDetails)){
                        isTrue = true;
                        field.set(menuElement,field.get(eDetails));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            menuRepository.save(menuElement);
        }

        return isTrue ? menuElement : eDetails;
    }

    @Override
    public boolean deleteElement(String id) {
        menuRepository.deleteById(id);
        Optional<Menu> menu = menuRepository.findById(id);
        return menu.isEmpty()
                ? false : true ;
    }

    @Override
    public long countElements() {
        return 0;
    }
}
