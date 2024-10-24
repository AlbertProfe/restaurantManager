package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRestaurantRepository menuRepository;

    @Override
    public List<MenuRestaurant> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public MenuRestaurant createMenu(MenuRestaurant menu) {
        return menuRepository.save(menu);
    }

    @Override
    public MenuRestaurant getMenuById(String id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public MenuRestaurant updateMenu(String id, MenuRestaurant menuDetails) {
        MenuRestaurant menu = getMenuById(id);
        if(menu== null) {
            return null;
        }
        return menuRepository.save(menuDetails);
    }

    @Override
    public void deleteMenu(String id) {
        menuRepository.deleteById(id);
    }

    @Override
    public long countMenus() {
        return menuRepository.count();
    }
}
