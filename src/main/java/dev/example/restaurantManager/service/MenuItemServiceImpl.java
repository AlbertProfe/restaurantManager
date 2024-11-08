package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Dessert;
import dev.example.restaurantManager.model.MainCourse;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.repository.DessertRepository;
import dev.example.restaurantManager.repository.MainCourseRepository;
import dev.example.restaurantManager.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;
    @Autowired
    DessertRepository dessertRepository;
    @Autowired
    MainCourseRepository mainCourseRepository;

    // Checking both repositories and put the results in a list
    @Override
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.addAll(dessertRepository.findAll());
        menuItems.addAll(mainCourseRepository.findAll());
        return menuItems;
    }

    // Save each instance in its own repository
    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        if (menuItem instanceof Dessert) {
            return dessertRepository.save((Dessert) menuItem);
        } else if (menuItem instanceof MainCourse) {
            return mainCourseRepository.save((MainCourse) menuItem);
        }
        throw new IllegalArgumentException("Unsupported menu item type");
    }

    @Override
    public MenuItem getMenuItemById(String id) {
        MenuItem menuItem = dessertRepository.findById(id).orElse(null);
        if (menuItem == null) {
            menuItem = mainCourseRepository.findById(id).orElse(null);
        }
        return menuItem;
    }

    @Override
    public MenuItem updateMenuItem(String id, MenuItem menuItemDetails, Dessert dessertDetails, MainCourse mainCourseDetails) {
        if (dessertRepository.existsById(id)) {
            Dessert dessert = dessertRepository.findById(id).orElse(null);
            if (dessert != null) {
                dessert.setName(dessertDetails.getName());
                dessert.setDescription(dessertDetails.getDescription());
                dessert.setPrice(dessertDetails.getPrice());
                dessert.setGlutenFree(dessertDetails.isGlutenFree());
                dessert.setGlutenFree(dessertDetails.isLactoseFree());
                return dessertRepository.save(dessert);
            }
        } else if (mainCourseRepository.existsById(id)) {
            MainCourse mainCourse = mainCourseRepository.findById(id).orElse(null);
            if (mainCourse != null) {
                mainCourse.setName(mainCourseDetails.getName());
                mainCourse.setDescription(mainCourseDetails.getDescription());
                mainCourse.setPrice(mainCourseDetails.getPrice());
                mainCourse.setVegan(mainCourseDetails.isVegan());
                mainCourse.setGlutenFree(mainCourseDetails.isGlutenFree());
                return mainCourseRepository.save(mainCourse);
            }
        }
        return null;
    }

    @Override
    public boolean deleteMenuItem(String id) {
        if (dessertRepository.existsById(id)) {
            dessertRepository.deleteById(id);
            return true;
        } else if (mainCourseRepository.existsById(id)) {
            mainCourseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long countMenuItems() {
        return dessertRepository.count() + mainCourseRepository.count();
    }
}