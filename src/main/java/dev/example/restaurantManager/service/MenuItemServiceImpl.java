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
@Service
public class MenuItemServiceImpl implements MenuItemService{

     @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    MenuRestaurantRepository menuRepository;

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        // not needed because it's already generated in the own entity
        //menuItem.setId(UUID.randomUUID().toString());
        Optional<MenuItem> menuItemCreated = menuItemRepository.findById(menuItem.getId());
        if(menuItemCreated.isPresent()){
            return null;
        }
        menuItemRepository.save(menuItem);
        return menuItem;
    }


    @Override
    public MenuItem getMenuItemById(String id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItem updateMenuItem(String id, MenuItem menuItemDetails) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);
        if (menuItem !=null){
            if (menuItemDetails.getName() != null){
                menuItem.setName(menuItemDetails.getName());
            }
            if (menuItemDetails.getDescription() != null){
                menuItem.setDescription(menuItemDetails.getDescription());
            }
            if (menuItemDetails.isSpicy()){
                menuItem.setSpicy(menuItemDetails.isSpicy());
            }
            if (menuItemDetails.isHasGluten()){
                menuItem.setHasGluten(menuItemDetails.isHasGluten());
            }
            if (menuItemDetails.isAvailable()){
                menuItem.setAvailable(menuItemDetails.isAvailable());
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

    @Override
    public List<MenuItem> getMenuItemsByMenuId(String menuId) {

        MenuRestaurant menu = menuRepository.findById(menuId).orElse(null);
        return menu != null ? menu.getMenuItems() : new ArrayList<>();
    }
}
