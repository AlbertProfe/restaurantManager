package dev.example.restaurantManager.service;


import dev.example.restaurantManager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menurePository;

    @Override
    public List<MenuService> getAllMenus() {
        return List.of();
    }

    @Override
    public MenuService createMenu() {
        return null;
    }

    @Override
    public MenuService getMenuByContent() {
        return null;
    }

    @Override
    public MenuService updateStatusMenu(boolean active) {
        return null;
    }

    @Override
    public boolean deleteMenu(String name) {
        return false;
    }
}
