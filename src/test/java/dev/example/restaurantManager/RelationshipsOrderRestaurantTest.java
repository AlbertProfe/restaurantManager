package dev.example.restaurantManager;

import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RelationshipsOrderRestaurantTest {

@Autowired
private TakeAwayOrderRepository takeAwayOrderRepository;
@Autowired
private CustomerRepository customerRepository;
@Autowired
private MenuRestaurantRepository menuRestaurantRepository;
@Autowired
private OrderRestaurantRepository orderRestaurantRepository;
@Autowired
private TableRestaurantRepository tableRestaurantRepository;
@Autowired
private EatInOrderRestaurantRepository eatInOrderRestaurantRepository;

        @Test
        public void TestCreateOrder() {
                // Create sample menus
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true, null);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02","Pizza Menu", 12.99, "Pizza and salad", true, false, null);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03","Salad Menu", 8.99, "Mixed salad and dressing", true, true, null);
                // Save sample menus
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);
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
                OrderRestaurant shippingOrder1 = new ShippingOrderRestaurant("SO1", new Date(), "John", 2, 21.98, false, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant1)), "123 Main St", "New York", "Mike");
                OrderRestaurant shippingOrder2 = new ShippingOrderRestaurant("SO2", new Date(), "Sarah", 1, 12.99, true, new ArrayList<>(Arrays.asList(menuRestaurant2)), "456 Elm St", "Los Angeles", "Tom");
                OrderRestaurant shippingOrder3 = new ShippingOrderRestaurant("SO3", new Date(), "Emily", 3, 32.97, false, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)), "789 Oak St", "Chicago", "Lisa");

                // Create 3 EatInOrder objects
                OrderRestaurant eatInOrder1 = new EatInOrderRestaurant("EO1", new Date(), "David", 4, 43.96, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant1, menuRestaurant2, menuRestaurant2)), new ArrayList<>(Arrays.asList(table1)));
                OrderRestaurant eatInOrder2 = new EatInOrderRestaurant("EO2", new Date(), "Anna", 2, 21.98, false, new ArrayList<>(Arrays.asList(menuRestaurant2, menuRestaurant3)), new ArrayList<>(Arrays.asList(table2)));
                OrderRestaurant eatInOrder3 = new EatInOrderRestaurant("EO3", new Date(), "Mark", 6, 65.94, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant1, menuRestaurant2, menuRestaurant2, menuRestaurant3, menuRestaurant3)), new ArrayList<>(Arrays.asList(table1, table2)));

                // Create 3 TakeAwayOrder objects
                OrderRestaurant takeAwayOrder1 = new TakeAwayOrder("TO1", new Date(), "Alice", 1, 10.99, true, new ArrayList<>(Arrays.asList(menuRestaurant1)), customer1 );
                OrderRestaurant takeAwayOrder2 = new TakeAwayOrder("TO2", new Date(), "Bob", 2, 21.98, false, new ArrayList<>(Arrays.asList(menuRestaurant2, menuRestaurant3)), customer3 );
                OrderRestaurant takeAwayOrder3 = new TakeAwayOrder("TO3", new Date(), "Charlie", 3, 32.97, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)), customer5);
                OrderRestaurant takeAwayOrder4 = new TakeAwayOrder("TO4", new Date(), "Charlie", 3, 32.97, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3, menuRestaurant3, menuRestaurant3, menuRestaurant3)), customer9);

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
                        true, new ArrayList<>(Arrays.asList(menuRestaurant1)), null );
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

        @Test
        public void TestCreateOrderMenu () {
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true, null);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02","Pizza Menu", 12.99, "Pizza and salad", true, false, null);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03","Salad Menu", 8.99, "Mixed salad and dressing", true, true, null);
                // Save sample menus
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);
                // Create 3 Order objects and assign menus
                OrderRestaurant orderRestaurant1 = new OrderRestaurant("O01", new Date(), "John", 4, 43.96, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)));
                OrderRestaurant orderRestaurant2 = new OrderRestaurant("O02", new Date(), "John", 4, 43.96, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant3)));
                OrderRestaurant orderRestaurant3 = new OrderRestaurant("O03", new Date(), "John", 4, 43.96, true, new ArrayList<>(Arrays.asList(menuRestaurant2)));

                // Save sample orders
                orderRestaurantRepository.save(orderRestaurant1);
                //orderRestaurantRepository.save(orderRestaurant2);
                //orderRestaurantRepository.save(orderRestaurant3);


                // when
                Optional<OrderRestaurant> found = orderRestaurantRepository.findById("O01");
                System.out.println("--------------------");
                System.out.println("Order ID: " + found.get().getId());
                System.out.println(found.get());
                // then
                assertThat(found).isPresent();
                assertThat(found.get().getMenus().get(0).getName().equals(menuRestaurant1.getName()));
        }

        // java.lang.StackOverflowError toString
        @Test
        public void TestCreateOrderMenu_stackOverflow () {
            // Create sample menus
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99,
                        "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02","Pizza Menu",
                        12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03","Salad Menu",
                        8.99, "Mixed salad and dressing", true, true);
                // Save sample menus
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);
                // Create 3 Order objects and assign menus
                OrderRestaurant orderRestaurant1 = new OrderRestaurant("O01", new Date(), "John", 4, 43.96,
                        true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)));
                OrderRestaurant orderRestaurant2 = new OrderRestaurant("O02", new Date(), "John", 4, 43.96, true,
                        new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant3)));
                OrderRestaurant orderRestaurant3 = new OrderRestaurant("O03", new Date(), "John", 4, 43.96, true,
                        new ArrayList<>(Arrays.asList(menuRestaurant2)));

                // Save sample orders
                orderRestaurantRepository.save(orderRestaurant1);
                //orderRestaurantRepository.save(orderRestaurant2);
                //orderRestaurantRepository.save(orderRestaurant3);

                // set menus to orders and save
                menuRestaurant1.getOrders().add(orderRestaurant1);
                menuRestaurantRepository.save(menuRestaurant1);

                // when
                Optional<OrderRestaurant> found = orderRestaurantRepository.findById("O01");
                System.out.println("--------------------");
                System.out.println("Order ID: " + found.get().getId());
                // stack-overflow toString does not work in this case
                //System.out.println(found.get());

                Optional<MenuRestaurant> menuFound = menuRestaurantRepository.findById("M01");
                System.out.println("--------------------");
                System.out.println("Menu ID: " + menuFound.get().getId());
                // stack-overflow toString does not work in this case
                //System.out.println(menuFound.get());

                // then
                assertThat(found).isPresent();
                assertThat(found.get().getMenus().get(0).getName().equals(menuRestaurant1.getName()));
        }

        // Adding Menus to an Order: Verifies that menus can be added to an order
        // and the association is correctly persisted in the database.
        @Test
        public void testAddingMenusToOrder() {
                // Create sample menus and save them
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
                // Save sample menus
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                // Create an order and add menus
                OrderRestaurant order = new OrderRestaurant("O01", new Date(), "John", 4,
                        43.96, true, new ArrayList<>());
                order.addMenu(menuRestaurant1);
                order.addMenu(menuRestaurant2);
                order.addMenu(menuRestaurant3);
                orderRestaurantRepository.save(order);

                // Retrieve the order and assert the menus
                Optional<OrderRestaurant> foundOrder = orderRestaurantRepository.findById("O01");
                assertThat(foundOrder).isPresent();
                assertThat(foundOrder.get().getMenus()).hasSize(3);
                // Since we've implemented equals() and hashCode() methods based on
                // the compilation of all fields except for orders.
                // This avoids potential circular reference issues while still providing a comprehensive comparison of the menu items.
                // we can now use contains() to check if the retrieved menus match the original ones
                assertThat(foundOrder.get().getMenus()).contains(menuRestaurant1, menuRestaurant2, menuRestaurant3);
                // Retrieve the menus and assert the order
                assertThat(foundOrder.get().getMenus())
                        .extracting("id")
                        .containsExactlyInAnyOrder("M01", "M02", "M03");
                assertThat(foundOrder.get().getMenus())
                        .usingElementComparator(Comparator.comparing(MenuRestaurant::getId))
                        .containsExactlyInAnyOrder(menuRestaurant1, menuRestaurant2, menuRestaurant3);
        }

        // Adding Menus to an Order: Verifies that menus can be added to an order
        @Test
        public void testAddingRepeatedMenusToOrder() {
                // Create sample menus and save them
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
                // Save sample menus
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                // Create an order and add menus
                OrderRestaurant order = new OrderRestaurant("O01", new Date(), "John", 4,
                        43.96, true, new ArrayList<>());
                order.addMenu(menuRestaurant1);
                order.addMenu(menuRestaurant2);
                order.addMenu(menuRestaurant2);
                order.addMenu(menuRestaurant3);
                order.addMenu(menuRestaurant3);
                order.addMenu(menuRestaurant3);
                orderRestaurantRepository.save(order);

                // Retrieve the order and assert the menus
                Optional<OrderRestaurant> foundOrder = orderRestaurantRepository.findById("O01");
                assertThat(foundOrder).isPresent();
                assertThat(foundOrder.get().getMenus()).hasSize(6);
                // Since we've implemented equals() and hashCode() methods based on
                // the compilation of all fields except for orders.
                // This avoids potential circular reference issues while still providing a comprehensive comparison of the menu items.
                // we can now use contains() to check if the retrieved menus match the original ones
                assertThat(foundOrder.get().getMenus()).contains(menuRestaurant1, menuRestaurant2, menuRestaurant2, menuRestaurant3, menuRestaurant3, menuRestaurant3);
                // Retrieve the menus and assert the order
                assertThat(foundOrder.get().getMenus())
                        .extracting("id")
                        .containsExactlyInAnyOrder("M01", "M02", "M02","M03, M03, M03");
                assertThat(foundOrder.get().getMenus())
                        .usingElementComparator(Comparator.comparing(MenuRestaurant::getId))
                        .containsExactlyInAnyOrder(menuRestaurant1, menuRestaurant2, menuRestaurant2, menuRestaurant3, menuRestaurant3, menuRestaurant3 );
        }

        // Removing Menus from an Order: Checks that menus can be removed from an order
        // and the changes are reflected in the database.
        @Test
        public void testRemovingMenusFromOrder() {
                // Create sample menus and save them
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                // Retrieve the updated order after deleting the menu
                System.out.println("List of menus AFTER DELETION:");
                menuRestaurantRepository.findAll().forEach(System.out::println);

                // Create an order with multiple menus and save it
                OrderRestaurant order = new OrderRestaurant("O01", new Date(), "John", 4,
                        43.96, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)));
                orderRestaurantRepository.save(order);

                // Retrieve the order, remove a menu, and save the updated order
                Optional<OrderRestaurant> foundOrder = orderRestaurantRepository.findById("O01");
                assertThat(foundOrder).isPresent();
                OrderRestaurant updatedOrder = foundOrder.get();
                // Let's remove the second menu
                //updatedOrder.removeMenu(menuRestaurant2);
                updatedOrder.removeMenu(updatedOrder.getMenus().get(1));
                orderRestaurantRepository.save(updatedOrder);

                // Retrieve the updated order and assert the menus
                Optional<OrderRestaurant> updatedOrderOptional = orderRestaurantRepository.findById("O01");
                // print the menus
                System.out.println("Menus: " + updatedOrderOptional.get().getMenus());
                // assert the menus have been removed
                assertThat(updatedOrderOptional).isPresent();
                assertThat(updatedOrderOptional.get().getMenus()).hasSize(2);
                assertThat(updatedOrderOptional.get().getMenus()).contains(menuRestaurant1, menuRestaurant3);
                assertThat(updatedOrderOptional.get().getMenus()).doesNotContain(menuRestaurant2);

                // Retrieve the updated order after deleting the menu
                System.out.println("List of menus AFTER DELETION:");
                menuRestaurantRepository.findAll().forEach(System.out::println);
        }

        @Test
        public void testRemovingMenus_NO_Relationship() {
                // Create sample menus and save them
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                // Create an order with multiple menus and save it
                OrderRestaurant order = new OrderRestaurant("O01", new Date(), "John", 4,
                        43.96, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2)));
                orderRestaurantRepository.save(order);

                // Retrieve the order, remove a menu, and save the updated order
                Optional<OrderRestaurant> foundOrder = orderRestaurantRepository.findById("O01");
                assertThat(foundOrder).isPresent();

                menuRestaurantRepository.delete(menuRestaurant3);
                // Retrieve the updated order after deleting the menu
                System.out.println("List of menus AFTER DELETION:");
                menuRestaurantRepository.findAll().forEach(System.out::println);

                // We CAN NOT delete a menu that has a relationship
                /*menuRestaurantRepository.delete(menuRestaurant2);
                // Retrieve the updated order after deleting the menu
                System.out.println("List of menus AFTER DELETION:");
                menuRestaurantRepository.findAll().forEach(System.out::println);*/



        }

        // Removing Menus from an Order: Checks that menus can be removed from an order
        // and the changes are reflected in the database.
        // BE CAREFUL! This test should fail.
        @Test
        public void testRemovingMenusFromOrder_butNotRelationship() {
                // Create sample menus and save them
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                // Create an order with multiple menus and save it
                OrderRestaurant order = new OrderRestaurant("O01", new Date(), "John", 4,
                        43.96, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)));
                orderRestaurantRepository.save(order);

                // Let's remove the second menu
                menuRestaurantRepository.delete(menuRestaurant2);

                // Retrieve the updated order after deleting the menu
                System.out.println("List of menus AFTER DELETION:");
                menuRestaurantRepository.findAll().forEach(System.out::println);

                // Retrieve the updated order and assert the menus
                Optional<OrderRestaurant> updatedOrderOptional = orderRestaurantRepository.findById("O01");
                // print the menus
                System.out.println("Menus: " + updatedOrderOptional.get().getMenus());
                // assert the menus have been removed
                assertThat(updatedOrderOptional).isPresent();
                assertThat(updatedOrderOptional.get().getMenus()).hasSize(2);

        }


        // Cascading Deletion of Menus and Orders: Ensures that the deletion of a menu
        // or an order correctly cascades to the associated records in the order-menu join table.
        /*@Test
        public void testCascadingDeletionOfMenusAndOrders() {
                // Create sample menus and save them
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true);
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                // Create an order with multiple menus and save it
                OrderRestaurant order = new OrderRestaurant("O01", new Date(), "John", 4, 43.96, true,
                        new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)));
                orderRestaurantRepository.save(order);

                // Delete the menu and verify the cascade deletion of the order-menu association
                Optional<OrderRestaurant> foundOrder = orderRestaurantRepository.findById("O01");
                assertThat(foundOrder).isPresent();
                OrderRestaurant updatedOrder = foundOrder.get();
                updatedOrder.removeMenu(menuRestaurant2);
                //menuRestaurantRepository.delete(menuRestaurant2);
                orderRestaurantRepository.save(updatedOrder);
                Optional<OrderRestaurant> updatedOrderOptional = orderRestaurantRepository.findById("O01");
                assertThat(updatedOrderOptional).isPresent();
                assertThat(updatedOrderOptional.get().getMenus()).hasSize(2);
                assertThat(updatedOrderOptional.get().getMenus()).contains(menuRestaurant1, menuRestaurant3);
                assertThat(updatedOrderOptional.get().getMenus()).doesNotContain(menuRestaurant2);

                // Delete the order and verify the cascade deletion of the order-menu association
                orderRestaurantRepository.delete(order);
                Optional<MenuRestaurant> menuRestaurant1Optional = menuRestaurantRepository.findById("M01");
                Optional<MenuRestaurant> menuRestaurant2Optional = menuRestaurantRepository.findById("M02");
                Optional<MenuRestaurant> menuRestaurant3Optional = menuRestaurantRepository.findById("M03");
                assertThat(menuRestaurant1Optional).isPresent();
                assertThat(menuRestaurant2Optional).isPresent();
                assertThat(menuRestaurant3Optional).isPresent();
                assertThat(menuRestaurant1Optional.get().getOrders()).isEmpty();
                assertThat(menuRestaurant2Optional.get().getOrders()).isEmpty();
                assertThat(menuRestaurant3Optional.get().getOrders()).isEmpty();
        }*/

        // Adding Menus to an EatInOrder: Verifies that menus can be added
        // to an EatInOrderRestaurant and the association is correctly persisted in the database.
        /*@Test
        public void testAddingMenusToEatInOrder() {
                // Create sample menus and save them
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true, null);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false, null);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true, null);
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                // Create sample tables and save them
                TableRestaurant table1 = new TableRestaurant("T1", "Window Table", 4, false);
                TableRestaurant table2 = new TableRestaurant("T2", "Corner Table", 2, true);
                tableRestaurantRepository.save(table1);
                tableRestaurantRepository.save(table2);

                // Create an EatInOrder and add menus
                EatInOrderRestaurant eatInOrder = new EatInOrderRestaurant("EO1", new Date(), "John", 4, 43.96, true, new ArrayList<>(), new ArrayList<>(Arrays.asList(table1, table2)));
                eatInOrder.addMenu(menuRestaurant1);
                eatInOrder.addMenu(menuRestaurant2);
                eatInOrder.addMenu(menuRestaurant3);
                eatInOrderRestaurantRepository.save(eatInOrder);

                // Retrieve the EatInOrder and assert the menus
                Optional<EatInOrderRestaurant> foundOrder = (Optional<EatInOrderRestaurant>) eatInOrderRestaurantRepository.findById("EO1");
                assertThat(foundOrder).isPresent();
                assertThat(foundOrder.get().getMenus()).hasSize(3);
                assertThat(foundOrder.get().getMenus()).contains(menuRestaurant1, menuRestaurant2, menuRestaurant3);
        }

        // Removing Menus from an EatInOrder: Checks that menus can be removed from an EatInOrder
        // and the changes are reflected in the database.
        @Test
        public void testRemovingMenusFromEatInOrder() {
                // Create sample menus and save them
                MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true, null);
                MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false, null);
                MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true, null);
                menuRestaurantRepository.save(menuRestaurant1);
                menuRestaurantRepository.save(menuRestaurant2);
                menuRestaurantRepository.save(menuRestaurant3);

                // Create sample tables and save them
                TableRestaurant table1 = new TableRestaurant("T1", "Window Table", 4, false);
                TableRestaurant table2 = new TableRestaurant("T2", "Corner Table", 2, true);
                tableRestaurantRepository.save(table1);
                tableRestaurantRepository.save(table2);

                // Create an EatInOrder with multiple menus and save it
                EatInOrderRestaurant eatInOrder = new EatInOrderRestaurant("EO1", new Date(), "John", 4, 43.96, true, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)), new ArrayList<>(Arrays.asList(table1, table2)));
                eatInOrderRestaurantRepository.save(eatInOrder);

                // Retrieve the EatInOrder, remove a menu, and save the updated order
                Optional<EatInOrderRestaurant> foundOrder = (Optional<EatInOrderRestaurant>) eatInOrderRestaurantRepository.findById("EO1");
                assertThat(foundOrder).isPresent();
                EatInOrderRestaurant updatedOrder = foundOrder.get();
                updatedOrder.removeMenu(menuRestaurant2);
                orderRestaurantRepository.save(updatedOrder);

                // Retrieve the updated EatInOrder and assert the menus
                Optional<EatInOrderRestaurant> updatedOrderOptional = (Optional<EatInOrderRestaurant>) eatInOrderRestaurantRepository.findById("EO1");
                assertThat(updatedOrderOptional).isPresent();
                assertThat(updatedOrderOptional.get().getMenus()).hasSize(2);
                assertThat(updatedOrderOptional.get().getMenus()).contains(menuRestaurant1, menuRestaurant3);
                assertThat(updatedOrderOptional.get().getMenus()).doesNotContain(menuRestaurant2);

        }*/

}
