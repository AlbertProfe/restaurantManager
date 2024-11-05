package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        menu.setId(UUID.randomUUID().toString());
        return menuRepository.save(menu);
    }

    @Override
    public MenuRestaurant getMenuById(String id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    public MenuRestaurant updateMenu(String id, MenuRestaurant menuDetails) {
        MenuRestaurant menu = menuRepository.findById(id).orElse(null);
        if (menu != null) {
            if (menuDetails.getName() != null) {
                menu.setName(menuDetails.getName());
            }
            if (menuDetails.getPrice() >= 0) {
                menu.setPrice(menuDetails.getPrice());
            }
            if (menuDetails.getContent() != null) {
                menu.setContent(menuDetails.getContent());
            }
            menu.setActive(menuDetails.isActive());
            menu.setWater(menuDetails.isWater());

            return menuRepository.save(menu);


        }
        return null;


    }
    @Override
    public boolean deleteMenu(String id){
        menuRepository.deleteById(id);
        Optional<MenuRestaurant> menu = menuRepository.findById(id);
        return menu.isEmpty()
                ?false : true ;
    }
    @Override
    public long countMenus(){
        return menuRepository.count();
    }

}