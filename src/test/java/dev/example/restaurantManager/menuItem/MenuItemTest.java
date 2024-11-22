package dev.example.restaurantManager.menuItem;

import dev.example.restaurantManager.model.Dessert;
import dev.example.restaurantManager.model.IMenuItem;
import dev.example.restaurantManager.model.MainCourse;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuItemTest {
    @Test
    public void testIMenuItemInterface() {
        IMenuItem mainCourse = new MainCourse(
                UUID.randomUUID().toString(),
                "Grilled Chicken",
                "Grilled chicken breast with roasted vegetables",
                15.99,
                "Some main course specific property"
        );
        IMenuItem dessert = new Dessert(
                UUID.randomUUID().toString(),
                "Chocolate Cake",
                "Moist chocolate cake with vanilla ice cream",
                6.99,
                "Some dessert specific property"
        );
        assertEquals(mainCourse.getName(), "Grilled Chicken");
        assertEquals(mainCourse.getDescription(), "Grilled chicken breast with roasted vegetables");
        assertEquals(mainCourse.getPrice(), 15.99, 0.01);
        assertEquals(dessert.getName(), "Chocolate Cake");
        assertEquals(dessert.getDescription(), "Moist chocolate cake with vanilla ice cream");
        assertEquals(dessert.getPrice(), 6.99, 0.01);
        mainCourse.setName("Grilled Steak");
        mainCourse.setDescription("Grilled steak with roasted potatoes");
        mainCourse.setPrice(19.99);
        dessert.setName("Cheesecake");
        dessert.setDescription("Creamy cheesecake with strawberry sauce");
        dessert.setPrice(7.99);
        assertEquals(mainCourse.getName(), "Grilled Steak");
        assertEquals(mainCourse.getDescription(), "Grilled steak with roasted potatoes");
        assertEquals(mainCourse.getPrice(), 19.99, 0.01);
        assertEquals(dessert.getName(), "Cheesecake");
        assertEquals(dessert.getDescription(), "Creamy cheesecake with strawberry sauce");
        assertEquals(dessert.getPrice(), 7.99, 0.01);
    }
}
