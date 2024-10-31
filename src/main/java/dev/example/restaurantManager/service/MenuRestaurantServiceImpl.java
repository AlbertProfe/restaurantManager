package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MenuRestaurantServiceImpl implements MenuRestaurantService {
    @Autowired
    private MenuRestaurantRepository menuRepository;


    @Override
    public MenuRestaurant getMenuById(String id) {
        Optional<MenuRestaurant> menu = menuRepository.findById(id);
        return menu.get();
    }

    @Override
    public MenuRestaurant createMenu(MenuRestaurant menu) {
        Optional<MenuRestaurant> menuOptional = menuRepository.findById(menu.getId());
        if (menuOptional.isPresent()) {
            return null;
        } else {
            menuRepository.save(menu);
            return menu;
        }
    }

    @Override
    public MenuRestaurant updateMenu(String id, MenuRestaurant menuDetails) {
        Optional<MenuRestaurant> menuOptional = menuRepository.findById(id);
        if (menuOptional.isPresent()) {
            MenuRestaurant menu = menuOptional.get();
            menu.setName(menuDetails.getName());
            menu.setPrice(menuDetails.getPrice());
            menu.setContent(menuDetails.getContent());
            menu.setActive(menuDetails.isActive());
            menu.setWater(menuDetails.isWater());
            menuRepository.save(menu);
            return menu;
        } else {
            return null;
        }
    }
    @Override
    public boolean deleteMenu(String id) {
        Optional<MenuRestaurant> menuOptional = menuRepository.findById(id);
        if (menuOptional.isPresent()) {
            MenuRestaurant menu = menuOptional.get();
            menuRepository.delete(menu);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public List<MenuRestaurant> getAllMenus() {
        List<MenuRestaurant> menus = menuRepository.findAll();
        return menus;
    }
    @Override
    public long countMenus() {
        return menuRepository.count();
    }
}
