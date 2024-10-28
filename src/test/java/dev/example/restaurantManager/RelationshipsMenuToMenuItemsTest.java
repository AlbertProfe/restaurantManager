package dev.example.restaurantManager;

import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RelationshipsMenuToMenuItemsTest {

        @Autowired
        private MenuRestaurantRepository menuRestaurantRepository;
        @Autowired
        private MenuItemRepository menuItemRepository;

        @Test
        public void TestCreateMenu() {
                // Create sample menus
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
                // Save sample menus
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);
                // Create a list of all menus
                ArrayList<MenuRestaurant> menus = new ArrayList<>();
                menus.addAll(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3));

                // Print the number of menus
                System.out.println("Menus");
                System.out.println("Total number of menus: " + menus.size() + " menus.");
                System.out.println("--------------------");
                // Print all menus
                for (MenuRestaurant menu : menus) {
                        System.out.println("Menu ID: " + menu.getId());
                        System.out.println(menu);
                        System.out.println("--------------------");
                }
        }

        @Test
        public void TestCreateMenuItems() {
                //Create menu items
                MenuItem item1 = new MenuItem("Burger", "A tasty beef burger", false, true, true, CourseType.MAIN);
                MenuItem item2 = new MenuItem("Fries", "Dulce de leche ice cream", false, true, true, CourseType.DESSERT);
                MenuItem item3 = new MenuItem("Pizza", "Delicious margherita pizza", false, true, true, CourseType.MAIN);
                MenuItem item4 = new MenuItem("Salad", "Fresh mixed salad", false, false, true, CourseType.STARTER);
                //Save menu items
                menuItemRepository.save(item1);
                menuItemRepository.save(item2);
                menuItemRepository.save(item3);
                menuItemRepository.save(item4);

                // Create a list of all menu items
                ArrayList<MenuItem> items = new ArrayList<>();
                items.addAll(Arrays.asList(item1, item2, item3, item4));

                // Print the number of menu items
                System.out.println("Menu items");
                System.out.println("Total number of menu items: " + items.size() + " items.");
                System.out.println("--------------------");
                // Print all menu items
                for (MenuItem item : items) {
                        System.out.println("Item ID: " + item.getId());
                        System.out.println(item);
                        System.out.println("--------------------");
                }
        }

        @Test
        public void TestAssignMenuItemToMenu() {

                //Create menu items
                MenuItem item1 = new MenuItem("Burger", "A tasty beef burger", false, true, true, CourseType.MAIN);
                MenuItem item2 = new MenuItem("Fries", "Dulce de leche ice cream", false, true, true, CourseType.DESSERT);
                MenuItem item3 = new MenuItem("Pizza", "Delicious margherita pizza", false, true, true, CourseType.MAIN);
                MenuItem item4 = new MenuItem("Salad", "Fresh mixed salad", false, false, true, CourseType.STARTER);
                //Save menu items
                menuItemRepository.save(item1);
                menuItemRepository.save(item2);
                menuItemRepository.save(item3);
                menuItemRepository.save(item4);
                // Create sample menus
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
                // Save sample menus
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                //Add items
                menuRestaurant1.addMenuItem(item1);
                menuRestaurant1.addMenuItem(item2);
                menuRestaurant2.addMenuItem(item3);
                menuRestaurant3.addMenuItem(item4);

                menuRestaurantRepository.saveAll(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3));

                // Create a list of all menus
                ArrayList<MenuRestaurant> menus = new ArrayList<>();
                menus.addAll(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3));

                // Print the number of menus
                System.out.println("Menus");
                System.out.println("Total number of menus: " + menus.size() + " menus.");
                System.out.println("--------------------");
                // Print all menus
                for (MenuRestaurant menu : menus) {
                        System.out.println("Menu ID: " + menu.getId());
                        System.out.println(menu);
                        System.out.println("--------------------");
                }

                //when
                Optional<MenuRestaurant> menuFound = menuRestaurantRepository.findById("M01");
                System.out.println("--------------------");
                System.out.println("Menu ID: " + menuFound.get().getId());

                Optional<MenuItem> menuItemOneFound = menuItemRepository.findById(String.valueOf(item1.getId()));
                System.out.println("--------------------");
                System.out.println("Menu Item ID: " + menuItemOneFound.get().getId());
                System.out.println("Menu Item Name: " + menuItemOneFound.get().getName());


                Optional<MenuItem> menuItemTwoFound = menuItemRepository.findById(String.valueOf(item2.getId()));
                System.out.println("--------------------");
                System.out.println("Menu Item ID: " + menuItemTwoFound.get().getId());
                System.out.println("Menu Item Name: " + menuItemTwoFound.get().getName());


                // then
                assertThat(menuFound).isPresent();
                assertThat(menuFound.get().getMenuItems().size()).isEqualTo(2);
                assertThat(menuFound.get().getMenuItems().get(0).getName()).isEqualTo(item1.getName());
                assertThat(menuFound.get().getMenuItems().get(1).getName()).isEqualTo(item2.getName());

        }


}