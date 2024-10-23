package dev.example.restaurantManager;


import dev.example.restaurantManager.model.MenuItemRestaurant;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import dev.example.restaurantManager.enums.CourseType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MenuAndMenuItemRelationshipTest {

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void testMenuAndMenuItemRelationship() {

        MenuRestaurant menuRestaurant = new MenuRestaurant();
        menuRestaurant.setId(UUID.randomUUID().toString());
        menuRestaurant.setName("Menu 1");
        menuRestaurant.setPrice(10.0);
        menuRestaurant.setContent("Content 1");
        menuRestaurant.setActive(true);
        menuRestaurant.setWater(true);


        menuRestaurantRepository.save(menuRestaurant);


        MenuItemRestaurant menuItemRestaurant = new MenuItemRestaurant();
        menuItemRestaurant.setName("Item 1");
        menuItemRestaurant.setDescription("Description 1");
        menuItemRestaurant.setPrice(5.0);
        menuItemRestaurant.setSpicy(false);
        menuItemRestaurant.setHasGluten(false);
        menuItemRestaurant.setAvailable(true);
        menuItemRestaurant.setCourseType(CourseType.STARTER);


        menuRestaurant.addMenuItem(menuItemRestaurant);


        menuRestaurantRepository.save(menuRestaurant);


        MenuRestaurant foundMenu = menuRestaurantRepository.findById(menuRestaurant.getId()).orElse(null);
        assertNotNull(foundMenu, "MenuRestaurant should not be null");


        assertFalse(foundMenu.getMenuItems().isEmpty(), "MenuRestaurant should have at least one MenuItemRestaurant");

        MenuItemRestaurant foundMenuItem = foundMenu.getMenuItems().get(0);
        assertNotNull(foundMenuItem, "MenuItemRestaurant should not be null");


        System.out.println(foundMenuItem.toString());
    }



}
