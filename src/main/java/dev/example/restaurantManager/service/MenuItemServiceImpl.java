package dev.example.restaurantManager.service;



import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    MenuRestaurantRepository menuRepository;

    @Override
    public List<MenuItem> getMenuItemsByMenuId(String menuId) {
        MenuRestaurant menu = menuRepository.findById(menuId).orElse(null);
        return menu != null ? menu.getMenuItems() : new ArrayList<>();
    }


    @Override

    public List<MenuItem> getAllMenuItems(){
        return menuItemRepository.findAll();
    }

    @Override

    public MenuItem createMenuItem(MenuItem menuItem){
        menuItem.setId(UUID.randomUUID().toString());
        return menuItemRepository.save(menuItem);
    }

    public MenuItem getMenuItemByID  (String id){
        return menuItemRepository.findById(id).orElse(null);
    }

    @Override

    public MenuItem updateMenuItem(String id, MenuItem menuItemDetails) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);
        if (menuItem != null) {
            if (menuItemDetails.getName() != null) {
                menuItem.setName(menuItemDetails.getName());
            }
            if (menuItemDetails.getDescription() != null) {
                menuItem.setDescription(menuItemDetails.getDescription());
            }
            menuItem.setSpicy(menuItemDetails.isSpicy());
            menuItem.setHasGluten(menuItemDetails.hasGluten());
            menuItem.setAvailable(menuItemDetails.isAvailable());


            if (menuItemDetails.getCourseType() != null) {
                menuItem.setCourseType(menuItemDetails.getCourseType());
            }

            return menuItemRepository.save(menuItem);
        }
        return null;
    }
    @Override
    public boolean deleteMenuItem(String id) {
        menuItemRepository.deleteById(id);
        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
        return menuItem.isEmpty()
                ? false : true ;
    }

    @Override
    public long countMenuItems() {
        return menuItemRepository.count();
    }



}


