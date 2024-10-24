package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepository itemRepository;

    @Override
    public List<MenuItem> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public MenuItem createItem(MenuItem item) {
        return itemRepository.save(item);
    }

    @Override
    public MenuItem getItemById(String id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItem updateItem(String id, MenuItem itemDetails) {
        MenuItem item = getItemById(id);
        if(item==null) {
            return null;
        }
        return itemRepository.save(itemDetails);
    }

    @Override
    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }

    @Override
    public long countItems() {
        return itemRepository.count();
    }
}
