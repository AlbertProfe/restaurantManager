package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.service.MenuRestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuRestaurantServiceImpl implements MenuRestaurantService {

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public MenuRestaurant createMenu(MenuRestaurant menuRestaurant) {
        return menuRestaurantRepository.save(menuRestaurant);
    }

    @Override
    public Optional<MenuRestaurant> getMenuById(String id) {
        return menuRestaurantRepository.findById(id);
    }

    @Override
    public List<MenuRestaurant> getAllMenus() {
        return menuRestaurantRepository.findAll();
    }

    @Override
    public MenuRestaurant updateMenu(MenuRestaurant menuRestaurant) {
        return menuRestaurantRepository.save(menuRestaurant);
    }

    @Override
    public void deleteMenu(String id) {
        menuRestaurantRepository.deleteById(id);
    }

    @Override
    public void addMenuItemToMenu(String menuId, MenuItem menuItem) {
        Optional<MenuRestaurant> menuRestaurantOpt = menuRestaurantRepository.findById(menuId);
        if (menuRestaurantOpt.isPresent()) {
            MenuRestaurant menuRestaurant = menuRestaurantOpt.get();
            menuRestaurant.addMenuItem(menuItem);
            menuRestaurantRepository.save(menuRestaurant);
        }
    }

    @Override
    public List<MenuItem> getMenuItems(String menuId) {
        Optional<MenuRestaurant> menuRestaurantOpt = menuRestaurantRepository.findById(menuId);
        return menuRestaurantOpt.map(MenuRestaurant::getMenuItems).orElse(null);
    }
}

