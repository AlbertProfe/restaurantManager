package dev.example.restaurantManager;

import dev.example.restaurantManager.model.CourseType;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import dev.example.restaurantManager.service.MenuItemService;
import dev.example.restaurantManager.service.MenuRestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MenuAndMenuItemServiceTest {

    @Autowired
    private MenuRestaurantService menuRestaurantService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    private MenuRestaurant testMenu;
    private MenuRestaurant testMenu2;
    private MenuItem testItem;

    @BeforeEach
    public void setup() {
        testMenu = new MenuRestaurant("1", "Lunch Menu", 19.99, "Main course and dessert", true, false);
        testMenu2 = new MenuRestaurant("2", "Dinner Menu", 19.99, "Main course and dessert", true, false);
        testItem = new MenuItem("Burger", "Beef burger with cheese", false, true, true, CourseType.MAIN);
    }

    @Test
    public void createMenuRestaurantTest() {
        MenuRestaurant createdMenu = menuRestaurantService.createMenu(testMenu);

        assertThat(createdMenu).isNotNull();
        assertThat(createdMenu.getId()).isNotNull();
        assertThat(createdMenu.getName()).isEqualTo(testMenu.getName());
    }

    @Test
    public void createMenuItemTest() {
        MenuItem createdItem = menuItemService.createMenuItem(testItem);

        assertThat(createdItem).isNotNull();
        assertThat(createdItem.getId()).isNotNull();
        assertThat(createdItem.getName()).isEqualTo(testItem.getName());
    }

    @Test
    public void getAllMenusTest() {
        menuRestaurantService.createMenu(testMenu);
        menuRestaurantService.createMenu(testMenu2);

        List<MenuRestaurant> allMenus = menuRestaurantService.getAllMenus();
        assertThat(allMenus).isNotEmpty();
        assertThat(allMenus).extracting(MenuRestaurant::getName).contains("Lunch Menu", "Dinner Menu");
    }

    @Test
    public void addMenuItemToMenuTest() {
        MenuRestaurant savedMenu = menuRestaurantService.createMenu(testMenu);
        MenuItem savedItem = menuItemService.createMenuItem(testItem);

        menuRestaurantService.addMenuItemToMenu(savedMenu.getId(), savedItem);

        Optional<MenuRestaurant> updatedMenu = menuRestaurantService.getMenuById(savedMenu.getId());
        assertThat(updatedMenu.get().getMenuItems()).contains(savedItem);
    }

    @Test
    public void getMenuItemsFromMenuTest() {
        MenuRestaurant savedMenu = menuRestaurantService.createMenu(testMenu);
        MenuItem savedItem1 = menuItemService.createMenuItem(new MenuItem("Pasta", "Spaghetti with tomato sauce", false, false, true, CourseType.MAIN));
        MenuItem savedItem2 = menuItemService.createMenuItem(new MenuItem("Ice Cream", "Vanilla ice cream", false, false, true, CourseType.DESSERT));

        menuRestaurantService.addMenuItemToMenu(savedMenu.getId(), savedItem1);
        menuRestaurantService.addMenuItemToMenu(savedMenu.getId(), savedItem2);

        List<MenuItem> menuItems = menuRestaurantService.getMenuItems(savedMenu.getId());
        assertThat(menuItems).contains(savedItem1, savedItem2);
    }


}
