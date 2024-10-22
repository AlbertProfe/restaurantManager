package dev.example.restaurantManager;


import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.*;

@DataJpaTest
public class MenuMenuItemTest {
    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void TestRelationshipMenuMenuItem(){

        // Create menus
        MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
        MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02","Pizza Menu", 12.99, "Pizza and salad", true, false);
        MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03","Salad Menu", 8.99, "Mixed salad and dressing", true, true);
        // Save menus
        menuRestaurantRepository.save(menuRestaurant1);
        menuRestaurantRepository.save(menuRestaurant2);
        menuRestaurantRepository.save(menuRestaurant3);

        // Create menu





    }


}
