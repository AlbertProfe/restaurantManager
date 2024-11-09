package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.MenuItem.MenuItem;
import dev.example.restaurantManager.repository.DessertRepository;
import dev.example.restaurantManager.repository.MainCourseRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class MenuRestaurantServiceImpl implements MenuRestaurantService {

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

    @Autowired
    private MainCourseRepository mainCourseRepository;

    @Autowired
    private DessertRepository dessertRepository;

    @Override
    public List<MenuRestaurant> getAllMenus() {
        return menuRestaurantRepository.findAll();
    }

    @Override
    public MenuRestaurant createMenu(MenuRestaurant menu) {
        menu.setId(UUID.randomUUID().toString());
        return menuRestaurantRepository.save(menu);
    }

    @Override
    public MenuRestaurant getMenuById(String id) {
        return menuRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public MenuRestaurant updateMenu(String id, MenuRestaurant menuDetails) {
        MenuRestaurant menu = menuRestaurantRepository.findById(id).orElse(null);
        if (menu != null) {
            menu.setName(menuDetails.getName());
            menu.setContent(menuDetails.getContent());
            return menuRestaurantRepository.save(menu);
        }
        return null;
    }

    @Override
    public boolean deleteMenu(String id) {
        if (menuRestaurantRepository.existsById(id)) {
            menuRestaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countMenus() {
        return menuRestaurantRepository.count();
    }


    private MenuItem getMenuItem(String menuItemId){
        if(mainCourseRepository.existsById(menuItemId)){
            return mainCourseRepository.findById(menuItemId).orElse(null);
        }
        if(dessertRepository.existsById(menuItemId)){
            return dessertRepository.findById(menuItemId).orElse(null);
        }
        return null;
    }

    @Override
    public MenuRestaurant addMenuItemToMenu(String menuId, String menuItemId) {
        MenuItem menuItem = getMenuItem(menuItemId);
        MenuRestaurant menu = menuRestaurantRepository.findById(menuId).orElse(null);
        if (menu != null && menuItem != null) {
            menu.getMenuItems().add(menuItem);
            return menuRestaurantRepository.save(menu);
        }
        return null;
    }

    @Override
    public MenuRestaurant removeMenuItemFromMenu(String menuId, String menuItemId) {
        MenuRestaurant menu = menuRestaurantRepository.findById(menuId).orElse(null);
        if (menu != null) {
            menu.getMenuItems().removeIf(item -> item.getId().equals(menuItemId));
            return menuRestaurantRepository.save(menu);
        }
        return null;
    }
}