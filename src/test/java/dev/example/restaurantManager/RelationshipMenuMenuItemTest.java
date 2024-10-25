package dev.example.restaurantManager;

import dev.example.restaurantManager.model.CourseType;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RelationshipMenuMenuItemTest {

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void TestRelationshipMenuMenuItem() {
        // Create MenuItems
        MenuItem item1 = new MenuItem("I01", "Tacos", "Fresh",true, false, true, CourseType.MAIN, new ArrayList<>());
        MenuItem item2 = new MenuItem("I02", "Brownie","Rich and fudgy brownie without gluten.", false, false, true, CourseType.DESSERT, new ArrayList<>() );
        MenuItem item3 = new MenuItem("I03", "Salad", "Classic Caesar salad with a creamy dressing.", false, true, false, CourseType.STARTER, new ArrayList<>());
        menuItemRepository.save(item1);
        menuItemRepository.save(item2);
        menuItemRepository.save(item3);

        // Create menus
        MenuRestaurant menu1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
        MenuRestaurant menu2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
        MenuRestaurant menu3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);

        // Adding items to menus
        menu1.addItem(menuItemRepository.findByName("Tacos"));
        menu1.addItem(menuItemRepository.findByName("Brownie"));
        menuRestaurantRepository.save(menu1);
        menu2.addItem(menuItemRepository.findByName("Brownie"));
        menuRestaurantRepository.save(menu2);
        menu3.addItem(menuItemRepository.findByName("Salad"));
        menuRestaurantRepository.save(menu3);

//        // Create a list of all menu
//        ArrayList<MenuRestaurant> menus = new ArrayList<>();
//        menus.addAll(Arrays.asList(menu1, menu2, menu3));
//        // Print the number of orders
//        System.out.println("Menus");
//        System.out.println("Total number of orders: " + menus.size() + " menus.");
//        System.out.println("--------------------");
//
//        // Create a list of all menuItems
//        ArrayList<MenuItem> menuItems = new ArrayList<>();
//        menuItems.addAll(Arrays.asList(item1, item2, item3));
//        // Print the number of items
//        System.out.println("Menu Items");
//        System.out.println("Total number of items: " + menuItems.size() + " items.");
//        System.out.println("--------------------");
//


        // MenuRestaurant
        // When
        Optional<MenuRestaurant> foundMenu = menuRestaurantRepository.findById("M01");
        System.out.println("--------------------");
        System.out.println("Menu ID: " + foundMenu.get().getId());
        System.out.println(foundMenu.get());
        // Then
        assertThat(foundMenu).isPresent();
        assertThat(foundMenu.get().getMenuItems().get(0).getName().equals(item1.getName()));

        // MenuItem
        // When

        Optional<MenuItem> foundItem = Optional.ofNullable(menuItemRepository.findByName("Tacos"));
        System.out.println("--------------------");
        System.out.println(menuItemRepository.count());
        //menuItemRepository.findAll().forEach(System.out::println);
        System.out.println(foundItem.isPresent());
        System.out.println("Item name: " + foundItem.get().getName());

        System.out.println(foundItem.get());
        // Then
        assertThat(foundItem).isPresent();


        //assertNotNull(foundItem, "Not null is expected");

    }


}
