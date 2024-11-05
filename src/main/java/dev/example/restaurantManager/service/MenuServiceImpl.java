package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    MenuRestaurantRepository menuRepository;

    @Override
    public List<MenuRestaurant> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public long countMenus() {
        return menuRepository.count();
    }

}
