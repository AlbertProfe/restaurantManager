package dev.example.restaurantManager;

import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.repository.TakeAwayOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RelationshipsOrderRestaurantTest {

@Autowired
private TakeAwayOrderRepository takeAwayOrderRepository;
@Autowired
private CustomerRepository customerRepository;

    @Test
    public void TestCreateOrder() {
            // Create sample menus
            Menu menu1 = new Menu("Burger Menu", 10.99, "Burger, fries, and drink", true, true);
            Menu menu2 = new Menu("Pizza Menu", 12.99, "Pizza and salad", true, false);
            Menu menu3 = new Menu("Salad Menu", 8.99, "Mixed salad and dressing", true, true);

            // Create sample customers
            Customer customer1 = new Customer("C1", "John", "john@email.com", "123-456-7890", 30, false, false);
            Customer customer2 = new Customer("C2", "Sarah", "sarah@email.com", "234-567-8901", 25, true, false);
            Customer customer3 = new Customer("C3", "Emily", "emily@email.com", "345-678-9012", 35, false, false);
            Customer customer4 = new Customer("C4", "David", "david@email.com", "456-789-0123", 40, true, false);
            Customer customer5 = new Customer("C5", "Anna", "anna@email.com", "567-890-1234", 28, false, false);
            Customer customer6 = new Customer("C6", "Mark", "mark@email.com", "678-901-2345", 45, true, false);
            Customer customer7 = new Customer("C7", "Paul", "paul@email.com", "789-012-3456", 33, false, false);
            Customer customer8 = new Customer("C8", "Linda", "linda@email.com", "890-123-4567", 38, true, false);
            Customer customer9 = new Customer("C9", "George", "george@email.com", "901-234-5678", 50, false, false);

            // Create sample tables
            TableRestaurant table1 = new TableRestaurant("T1", "Window Table", 4, false);
            TableRestaurant table2 = new TableRestaurant("T2", "Corner Table", 2, true);

            // Create 3 ShippingOrder objects
            OrderRestaurant shippingOrder1 = new ShippingOrderRestaurant("SO1", new Date(), "John", 2, 21.98, false, new ArrayList<>(Arrays.asList(menu1, menu1)), "123 Main St", "New York", "Mike");
            OrderRestaurant shippingOrder2 = new ShippingOrderRestaurant("SO2", new Date(), "Sarah", 1, 12.99, true, new ArrayList<>(Arrays.asList(menu2)), "456 Elm St", "Los Angeles", "Tom");
            OrderRestaurant shippingOrder3 = new ShippingOrderRestaurant("SO3", new Date(), "Emily", 3, 32.97, false, new ArrayList<>(Arrays.asList(menu1, menu2, menu3)), "789 Oak St", "Chicago", "Lisa");

            // Create 3 EatInOrder objects
            OrderRestaurant eatInOrder1 = new EatInOrderRestaurant("EO1", new Date(), "David", 4, 43.96, true, new ArrayList<>(Arrays.asList(menu1, menu1, menu2, menu2)), table1);
            OrderRestaurant eatInOrder2 = new EatInOrderRestaurant("EO2", new Date(), "Anna", 2, 21.98, false, new ArrayList<>(Arrays.asList(menu2, menu3)), table2);
            OrderRestaurant eatInOrder3 = new EatInOrderRestaurant("EO3", new Date(), "Mark", 6, 65.94, true, new ArrayList<>(Arrays.asList(menu1, menu1, menu2, menu2, menu3, menu3)), table1);

            // Create 3 TakeAwayOrder objects
            OrderRestaurant takeAwayOrder1 = new TakeAwayOrder("TO1", new Date(), "Alice", 1, 10.99, true, new ArrayList<>(Arrays.asList(menu1)), customer1 );
            OrderRestaurant takeAwayOrder2 = new TakeAwayOrder("TO2", new Date(), "Bob", 2, 21.98, false, new ArrayList<>(Arrays.asList(menu2, menu3)), customer3 );
            OrderRestaurant takeAwayOrder3 = new TakeAwayOrder("TO3", new Date(), "Charlie", 3, 32.97, true, new ArrayList<>(Arrays.asList(menu1, menu2, menu3)), customer5);
            OrderRestaurant takeAwayOrder4 = new TakeAwayOrder("TO4", new Date(), "Charlie", 3, 32.97, true, new ArrayList<>(Arrays.asList(menu1, menu2, menu3, menu3, menu3, menu3)), customer9);

            // Create a list of all orders
            ArrayList<OrderRestaurant> orders = new ArrayList<>();
            orders.addAll(Arrays.asList(shippingOrder1, shippingOrder2, shippingOrder3,
                    eatInOrder1, eatInOrder2, eatInOrder3,
                    takeAwayOrder1, takeAwayOrder2, takeAwayOrder3, takeAwayOrder4));

            // Print the number of orders
            System.out.println("Orders");
            System.out.println("Total number of orders: " + orders.size() + " orders.");
            System.out.println("--------------------");
            // Print all orders
            for (OrderRestaurant order : orders) {
                System.out.println("Order ID: " + order.getId());
                System.out.println(order);
                System.out.println("--------------------");
            }

            // Save the customer with JPA Repository to use in order T11
            customerRepository.save(customer1);
            // let's create an order to save and test
            // we do not create orderToSave as OrderRestaurant but
            // as TakeAwayOrder to AVOID casting because
            // in this test is easier to work with
            TakeAwayOrder orderToSave = new TakeAwayOrder(
                    "T11", new Date(), "Alice", 1, 10.99,
                    true, new ArrayList<>(Arrays.asList(menu1)), null );
            // we assign the customer to the order
            //((TakeAwayOrder) orderToSave).setCustomerTakeAway(customer1);
            orderToSave.setCustomerTakeAway(customer1);
            // Save the order with JPA Repository
            //takeAwayOrderRepository.save(orderToSave);
            takeAwayOrderRepository.save(orderToSave);


            // when
            Optional<TakeAwayOrder> found = takeAwayOrderRepository.findById("T11");
            System.out.println("--------------------");
            System.out.println("TakeAwayOrder ID: " + found.get().getId());
            System.out.println(found.get());
            // then
            assertThat(found).isPresent();
            assertThat(found.get().getCustomerTakeAway().getName().equals(customer1.getName()));

            System.out.println("--------------------");
    }

}
