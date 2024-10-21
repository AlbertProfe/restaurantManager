package dev.example.restaurantManager.service;



import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

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
            if (menuDetails.getName() != null) {
                menu.setName(menuDetails.getName());
            }
/*            if (menuDetails.getEmail() != null) {
                menu.setEmail(menuDetails.getEmail());
            }
            if (menuDetails.getPhoneNumber() != null) {
                menu.setPhoneNumber(menuDetails.getPhoneNumber());
            }*/
            return menuRestaurantRepository.save(menu);
        }
        return null;
    }

    @Override
    public boolean deleteMenu(String id) {
        menuRestaurantRepository.deleteById(id);
        Optional<MenuRestaurant> menu = menuRestaurantRepository.findById(id);
        return menu.isEmpty()
                ? false : true ;
    }

    @Override
    public long countMenus() {
        return menuRestaurantRepository.count();
    }


}
