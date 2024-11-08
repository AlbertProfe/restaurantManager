package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem.MenuItem;
import dev.example.restaurantManager.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        menuItem.setId(UUID.randomUUID().toString());
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem getMenuItemById(String id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItem updateMenuItem(String id, MenuItem menuItemDetails) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);
        if (menuItem != null) {
            menuItem.setName(menuItemDetails.getName());
            menuItem.setDescription(menuItemDetails.getDescription());
            menuItem.setPrice(menuItemDetails.getPrice());
            return menuItemRepository.save(menuItem);
        }
        return null;
    }

    @Override
    public boolean deleteMenuItem(String id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countMenuItems() {
        return menuItemRepository.count();
    }
}