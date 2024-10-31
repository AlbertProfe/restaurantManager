package dev.example.restaurantManager;

import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import dev.example.restaurantManager.repository.OrderMenuQtyRepository;
import dev.example.restaurantManager.repository.OrderRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
public class RelationshipWithOrderMenuQtyTest {

    @Autowired
    private OrderMenuQtyRepository orderMenuQtyRepository;
    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;
    @Autowired
    private OrderRestaurantRepository orderRestaurantRepository;

    @Test
    public void testRelationshipWithOrderMenuQty() {
        // Create sample menus and save them
        MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
        MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
        MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
        menuRestaurantRepository.save(menuRestaurant1);
        menuRestaurantRepository.save(menuRestaurant2);
        menuRestaurantRepository.save(menuRestaurant3);

        // Create an order with multiple menus and save it
        OrderRestaurant order = new OrderRestaurant("O01", new Date(), "John", 4, 43.96, true, null);
        OrderRestaurant order2 = new OrderRestaurant("002", new Date(), "Malu", 4, 47.89, true, null);
        OrderRestaurant order3 = new OrderRestaurant("003", new Date(), "Malfoy", 4, 49.89, true, null);
        orderRestaurantRepository.save(order);
        orderRestaurantRepository.save(order2);
        orderRestaurantRepository.save(order3);

        OrderMenuQty orderMenuQty1 = new OrderMenuQty(4, null, null);
        OrderMenuQty orderMenuQty2 = new OrderMenuQty(5, null, null);
        OrderMenuQty orderMenuQty3 = new OrderMenuQty(6, null, null);

        orderMenuQty1.setMenuRestaurant(menuRestaurant1);
        orderMenuQty2.setMenuRestaurant(menuRestaurant2);
        orderMenuQty3.setMenuRestaurant(menuRestaurant3);

        orderMenuQty1.setOrderRestaurant(order);
        orderMenuQty2.setOrderRestaurant(order2);
        orderMenuQty3.setOrderRestaurant(order3);

        orderMenuQtyRepository.save(orderMenuQty1);
        orderMenuQtyRepository.save(orderMenuQty2);
        orderMenuQtyRepository.save(orderMenuQty3);

        System.out.println(orderMenuQty1.toString());
        order.addOrderMenuQty(orderMenuQty1);
        order.addOrderMenuQty(orderMenuQty2);
        order.addOrderMenuQty(orderMenuQty3);
        System.out.println(order.toString());
        order.getOrderMenuQtyIds().forEach(System.out::println);


    }
}
