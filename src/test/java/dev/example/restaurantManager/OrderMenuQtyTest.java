package dev.example.restaurantManager;

import static org.junit.jupiter.api.Assertions.*;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.model.OrderRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class OrderMenuQtyTest {

    private OrderMenuQty orderMenuQty;
    private OrderRestaurant orderRestaurant;
    private MenuRestaurant menuRestaurant;

    @BeforeEach
    public void setUp() {
        // Set up a sample OrderRestaurant object
        orderRestaurant = new OrderRestaurant();
        orderRestaurant.setId("1");
        orderRestaurant.setDate(new Date());
        orderRestaurant.setWaiter("John Doe");
        orderRestaurant.setPeopleQty(2);
        orderRestaurant.setTotalPayment(100.0);
        orderRestaurant.setPaid(false);

        // Set up a sample MenuRestaurant object
        menuRestaurant = new MenuRestaurant();
        menuRestaurant.setId("menu1");
        menuRestaurant.setName("Spaghetti");
        menuRestaurant.setPrice(12.99);
        menuRestaurant.setContent("Pasta with tomato sauce");
        menuRestaurant.setActive(true);
        menuRestaurant.setWater(false);

        // Set up the OrderMenuQty object
        orderMenuQty = new OrderMenuQty();
        orderMenuQty.setQuantity(3); // Set quantity
        orderMenuQty.setOrderRestaurantMapped(orderRestaurant); // Set the order relationship
        orderMenuQty.setMenuRestaurantMapped(menuRestaurant); // Set the menu relationship
    }

    @Test
    public void testOrderMenuQtyCreation() {
        assertNotNull(orderMenuQty);
        assertEquals(3, orderMenuQty.getQuantity());
        assertNotNull(orderMenuQty.getOrderRestaurantMapped());
        assertEquals("1", orderMenuQty.getOrderRestaurantMapped().getId());
        assertNotNull(orderMenuQty.getMenuRestaurantMapped());
        assertEquals("menu1", orderMenuQty.getMenuRestaurantMapped().getId());
    }

    @Test
    public void testOrderMenuQtyRelationship() {
        assertEquals(orderRestaurant, orderMenuQty.getOrderRestaurantMapped());
        assertEquals(menuRestaurant, orderMenuQty.getMenuRestaurantMapped());
    }

    @Test
    public void testSettersAndGetters() {
        orderMenuQty.setQuantity(5);
        assertEquals(5, orderMenuQty.getQuantity());

        OrderRestaurant newOrderRestaurant = new OrderRestaurant();
        newOrderRestaurant.setId("2");
        orderMenuQty.setOrderRestaurantMapped(newOrderRestaurant);
        assertEquals("2", orderMenuQty.getOrderRestaurantMapped().getId());

        MenuRestaurant newMenuRestaurant = new MenuRestaurant();
        newMenuRestaurant.setId("menu2");
        orderMenuQty.setMenuRestaurantMapped(newMenuRestaurant);
        assertEquals("menu2", orderMenuQty.getMenuRestaurantMapped().getId());
    }

    @Test
    public void testEqualsAndHashCode() {
        OrderMenuQty anotherOrderMenuQty = new OrderMenuQty();
        anotherOrderMenuQty.setId(orderMenuQty.getId());
        anotherOrderMenuQty.setQuantity(3);
        anotherOrderMenuQty.setOrderRestaurantMapped(orderRestaurant);
        anotherOrderMenuQty.setMenuRestaurantMapped(menuRestaurant);

        assertEquals(orderMenuQty, anotherOrderMenuQty);
        assertEquals(orderMenuQty.hashCode(), anotherOrderMenuQty.hashCode());

        anotherOrderMenuQty.setQuantity(4);
        assertNotEquals(orderMenuQty, anotherOrderMenuQty);
    }


    @Test
    public void testAddOrderMenuQty() {
        // Add OrderMenuQty to MenuRestaurant
        menuRestaurant.addOrderMenuQty(orderMenuQty);

        // Assert that the OrderMenuQty is added
        assertEquals(1, menuRestaurant.getOrderMenuQty().size());
        assertEquals(orderMenuQty, menuRestaurant.getOrderMenuQty().get(0));

        // Assert that the menuRestaurantMapped property is set correctly in OrderMenuQty
        assertEquals(menuRestaurant, orderMenuQty.getMenuRestaurantMapped());
    }
}
