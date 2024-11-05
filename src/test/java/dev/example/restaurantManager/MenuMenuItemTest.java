package dev.example.restaurantManager;


import dev.example.restaurantManager.model.CourseEnum;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

@DataJpaTest
public class MenuMenuItemTest {

    @Autowired
    private MenuRestaurantRepository menuRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void TestCreateMenu(){

        //Create Menu Item

        MenuItem menuItem1 = new MenuItem("item1", "Macarrones con tomate", "gratinados al horno", false, true, true, CourseEnum.MAIN, new ArrayList<>());
        menuItemRepository.save(menuItem1);
        // Create Menu
        MenuRestaurant menu1 = new MenuRestaurant("M01", "Menu básico", 10.0,"Un plato y bebida", true,true,new ArrayList<>());

        // add menuitem to menu
        menu1.addMenuItem(menuItem1);
        menuRepository.save(menu1);

        //verify the relationship

        Optional<MenuRestaurant> foundMenu = menuRepository.findById("M01");
        System.out.println("Menu1: " );
        System.out.println(foundMenu.get());
        System.out.println("--------------------");

        // Check that Menu is found
        assertThat(foundMenu).isPresent();
        //assertThat(foundMenu.get().getMenuItems()).hasSize(1);
        assertThat(foundMenu.get().getMenuItems()).contains(menuItem1);

    }

}