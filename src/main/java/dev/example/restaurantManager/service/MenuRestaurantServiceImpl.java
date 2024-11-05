package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class MenuRestaurantServiceImpl implements MenuRestaurantService {

    @Autowired
    private MenuRestaurantRepository menuRepository;

    @Override
    public List<MenuRestaurant> getAllMenus() {
        return menuRepository.findAll();
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
    public MenuRestaurant getMenuById(String id) {
        return menuRepository.findById(id).orElse(null);
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
        menuRepository.deleteById(id);
        Optional<MenuRestaurant> menu = menuRepository.findById(id);

        return menu.isPresent();
    }
    @Override
    public long countMenus() {
        return menuRepository.count();
    }

}