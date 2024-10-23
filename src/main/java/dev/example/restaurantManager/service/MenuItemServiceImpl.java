package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItemRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;

    public List<MenuItemRestaurant> getAllItemMenus(){
        List<MenuItemRestaurant> menuItemList = menuItemRepository.findAll();
        return menuItemList;
    }
    public MenuItemRestaurant getItemMenuById(String id){
        Optional<MenuItemRestaurant> menuItem = menuItemRepository.findById(id);
        return menuItem.orElse(null);
    }
    public MenuItemRestaurant createItemMenu(MenuItemRestaurant menuItemRestaurant){
        Optional<MenuItemRestaurant> menuItem = menuItemRepository.findById(menuItemRestaurant.getId());
        if(menuItem.isPresent()){
            return null;
        }
        menuItemRepository.save(menuItemRestaurant);
        return menuItemRestaurant;
    }
    public MenuItemRestaurant updateItemMenu(String id, MenuItemRestaurant menuItemRestaurant){
        Optional<MenuItemRestaurant> menuItem = menuItemRepository.findById(id);
        if(menuItem.isPresent()){
            menuItem.get().setName(menuItemRestaurant.getName());
            menuItem.get().setDescription(menuItemRestaurant.getDescription());
            menuItem.get().setPrice(menuItemRestaurant.getPrice());
            menuItemRepository.save(menuItem.get());
            return menuItem.get();
        }else{
            return null;
        }
    }
    public boolean deleteItemMenu(String id){
        menuItemRepository.deleteById(id);
        Optional<MenuItemRestaurant> menuItem = menuItemRepository.findById(id);
        return menuItem.isEmpty() ? false : true;
    }
   public long countItemMenus(){
       return menuItemRepository.count();
   }
}
