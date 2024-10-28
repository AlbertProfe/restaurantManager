package dev.example.restaurantManager;

import dev.example.restaurantManager.model.CourseType;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.service.MenuItemService;
import dev.example.restaurantManager.service.MenuRestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class MenuAndMenuItemServiceLayerTest {

    @Autowired
    private MenuRestaurantService menuRestaurantService;
    @Autowired
    private MenuItemService menuItemService;

    @Test
    public void testAddMenuItemToMenu() {
        MenuRestaurant menu = new MenuRestaurant("M04", "Vegan Menu", 15.99, "Salad and juice", true, true);
        MenuItem item = new MenuItem("Juice", "Freshly squeezed juice", false, true, true, CourseType.STARTER);

        menuRestaurantService.createMenu(menu);
        menuItemService.createMenuItem(item);
        menuRestaurantService.addMenuItemToMenu("M04", item);

        List<MenuItem> items = menuRestaurantService.getMenuItems("M04");
        assertThat(items).isNotEmpty();
    }

}
