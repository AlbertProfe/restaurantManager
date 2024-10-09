package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(String id, Menu menuDetails) {
        Menu oldMenu = menuRepository.findById(id).orElse(null);
        if (oldMenu == null){
            return null;
        }

        // TODO lógica para comprobar si se deben hacer las modificaciones

        return menuRepository.save(menuDetails);
    }

    @Override
    public void deleteMenu(String id) {
        menuRepository.deleteById(id);
    }

    @Override
    public Menu getMenuById(String id) {
        return menuRepository.findById(id).orElse(null);
    }
}
