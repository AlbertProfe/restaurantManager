/*
package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class DataLoader {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TableRestaurantRepository tableRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MenuRestaurantRepository menuRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private OrderRestaurantRepository orderRepository;
    @Autowired
    private OrderMenuQtyRepository orderMenuQtyRepository;
    @Autowired
    private EatInOrderRestaurantRepository eatInOrderRepository;
    @Autowired
    private ShippingOrderRepository shippingOrderRepository;
    @Autowired
    private TakeAwayOrderRepository takeAwayOrderRepository;
    private final Faker faker = new Faker(new Locale("en-US"));

    // master method orchestrating all other methods to
    // load fake data into the local H2 database
    // and create relationships between entities in the database
    public void loadAllData() {
        // let's create some fake data
        // for the inverse entities first
        // then create relationships between them
        createCustomers();
        createTables();
        createMenuItems();

        // create and assign menu items
        createMenusAndAssignMenuItems();
        // create bookings and assign customers and tables
        createBookingsAndAssignTablesAndCustomers();
        // create orders and assign menus and customers
        createOrdersAndAssignMenus();

        // create eat-in orders, shipping orders and take away orders
        createEatInOrders();
        createShippingOrders();
        createTakeAwayOrders();
    }

    // we are going to create 25 customers
    // and save them in the H2 local database
    private void createCustomers() {
        for (int i = 0; i < 25; i++) {
            Customer customer = new Customer(
                    UUID.randomUUID().toString(),
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().cellPhone(),
                    faker.random().nextInt(18, 80),
                    faker.random().nextBoolean(),
                    faker.random().nextBoolean()
            );
            customerRepository.save(customer);
        }
    }

    // we are going to create 10 tables
    // and save them in the H2 local database
    private void createTables() {
        for (int i = 0; i < 10; i++) {
            TableRestaurant table = new TableRestaurant(
                    UUID.randomUUID().toString(),
                    " Table-00" + (i + 1),
                    faker.options().option("Table Type Outdoor from Cisco INC",
                            "Table Type Indoor from Cisco INC",
                            "Table Rounded Big",
                            "Table Little Indoor"),
                    faker.random().nextInt(2, 8),
                    faker.bool().bool()
            );
            tableRepository.save(table);
        }
    }

    // we are going to create 25 menu items
    // and save them in the H2 local database
    private void createMenuItems() {
        for (int i = 0; i < 25; i++) {
            MenuItem menuItem = new MenuItem(
                    UUID.randomUUID().toString(),
                    faker.food().dish(),
                    faker.food().ingredient() + " " + faker.food().ingredient() ,
                    faker.number().randomDouble(2, 5, 30)
            );
            menuItemRepository.save(menuItem);
        }
    }

    // we are going to create 15 menus
    // and assign 5 to 10 menu items to each menu
    // to create a many-to-many relationship
    private void createMenusAndAssignMenuItems() {
        List<MenuItem> allItems = menuItemRepository.findAll();
        for (int i = 0; i < 15; i++) {
            MenuRestaurant menu = new MenuRestaurant(
                    UUID.randomUUID().toString(),
                     " Menu-00" + (i + 1),
                    faker.lorem().paragraph()
            );
            List<MenuItem> menuItems = new ArrayList<>(faker.random().nextInt(5, 10));
            for (int j = 0; j < faker.random().nextInt(5, 10); j++) {
                menuItems.add(allItems.get(faker.random().nextInt(allItems.size())));
            }
            menu.setMenuItems(menuItems);
            menuRepository.save(menu);
        }
    }

    // we are going to create 25 bookings
    // and assign a customer and table
    // to create a one-to-many relationship and then
    // save the booking in the database
    private void createBookingsAndAssignTablesAndCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<TableRestaurant> tables = tableRepository.findAll();
        for (int i = 0; i < 25; i++) {
            Booking booking = new Booking();
            // set all fields except 'orders'
            booking.setId(UUID.randomUUID().toString());
            booking.setShift(faker.options().option("Lunch", "Dinner"));
            booking.setDate(new Date());
            booking.setPeopleQty( faker.random().nextInt(1, 8));
            // set date in the future
            booking.setBookingDate(faker.date().future(faker.random().nextInt(1, 400), TimeUnit.DAYS));
            // set customer and table
            booking.setCustomerMapped(customers.get(faker.random().nextInt(customers.size())));
            booking.setTableRestaurantMapped(tables.get(faker.random().nextInt(tables.size())));
            bookingRepository.save(booking);
        }
    }

    // we are going to create 45 orders and then
    // create order menu quantities for each order
    // to create a many-to-many relationship
    private void createOrdersAndAssignMenus() {
        List<MenuRestaurant> menus = menuRepository.findAll();
        for (int i = 0; i < 45; i++) {
            OrderRestaurant order = new OrderRestaurant(
                    UUID.randomUUID().toString(),
                    new Date(),
                    faker.name().fullName(),
                    faker.random().nextInt(1, 8),
                    faker.number().randomDouble(2, 5, 50),
                    faker.bool().bool()
            );

            List<OrderMenuQty> orderMenuQties = new ArrayList<>();
            for (int j = 0; j < faker.random().nextInt(1, 5); j++) {
                // create order menu quantities for each order
                // to create a many-to-many relationship
                OrderMenuQty orderMenuQty = new OrderMenuQty();
                // set order and menu
                orderMenuQty.setId(UUID.randomUUID().toString());
                orderMenuQty.setOrder(order);
                orderMenuQty.setMenu(menus.get(faker.random().nextInt(menus.size())));
                // set quantity
                orderMenuQty.setQuantity(faker.random().nextInt(1, 5));
                // add to list
                orderMenuQties.add(orderMenuQty);
            }
            // set order menu quantities for each order
            // to create a many-to-many relationship
            // that is, one order will have the same menus repeated
            order.setOrderMenuQties(orderMenuQties);
            orderRepository.save(order);
        }
    }

    // we are going to create 25 eat-in orders
    // and save them in the H2 local database
    private void createEatInOrders() {
        List<Customer> customers = customerRepository.findAll();
        List<TableRestaurant> tables = tableRepository.findAll();
        List<MenuRestaurant> menus = menuRepository.findAll();

        for (int i = 0; i < 25; i++) {
            EatInOrderRestaurant order = new EatInOrderRestaurant();
            order.setId(UUID.randomUUID().toString());
            order.setDate(new Date());
            order.setWaiter(faker.name().fullName());
            order.setPeopleQty(faker.random().nextInt(1, 8));
            order.setTotalPayment(faker.number().randomDouble(2, 10, 200));
            order.setPaid(faker.bool().bool());

            // set table
            order.setTableRestaurant(tables.get(faker.random().nextInt(tables.size())));

            // Create OrderMenuQty entries
            List<OrderMenuQty> orderMenuQties = new ArrayList<>();
            for (int j = 0; j < faker.random().nextInt(1, 5); j++) {
                OrderMenuQty orderMenuQty = new OrderMenuQty();
                orderMenuQty.setId(UUID.randomUUID().toString());
                orderMenuQty.setOrder(order);
                orderMenuQty.setMenu(menus.get(faker.random().nextInt(menus.size())));
                orderMenuQty.setQuantity(faker.random().nextInt(1, 5));
                orderMenuQties.add(orderMenuQty);
            }
            order.setOrderMenuQties(orderMenuQties);

            eatInOrderRepository.save(order);
            orderMenuQtyRepository.saveAll(orderMenuQties);
        }
    }

    // we are going to create 25 shipping orders
    // and save them in the H2 local database
    private void createShippingOrders() {
        List<Customer> customers = customerRepository.findAll();
        List<MenuRestaurant> menus = menuRepository.findAll();

        for (int i = 0; i < 25; i++) {
            ShippingOrderRestaurant order = new ShippingOrderRestaurant();
            order.setId(UUID.randomUUID().toString());
            order.setDate(new Date());
            order.setWaiter(faker.name().fullName());
            order.setPeopleQty(faker.random().nextInt(1, 8));
            order.setTotalPayment(faker.number().randomDouble(2, 10, 200));
            order.setPaid(faker.bool().bool());
            order.setAddress(faker.address().fullAddress());
            // Set customer
            order.setCustomerShipping(customers.get(faker.random().nextInt(customers.size())));

            // Create OrderMenuQty entries
            List<OrderMenuQty> orderMenuQties = new ArrayList<>();
            for (int j = 0; j < faker.random().nextInt(1, 5); j++) {
                OrderMenuQty orderMenuQty = new OrderMenuQty();
                orderMenuQty.setId(UUID.randomUUID().toString());
                orderMenuQty.setOrder(order);
                orderMenuQty.setMenu(menus.get(faker.random().nextInt(menus.size())));
                orderMenuQty.setQuantity(faker.random().nextInt(1, 5));
                orderMenuQties.add(orderMenuQty);
            }
            order.setOrderMenuQties(orderMenuQties);

            shippingOrderRepository.save(order);
            orderMenuQtyRepository.saveAll(orderMenuQties);
        }
    }

    // we are going to create 25 take-away orders
    // and save them in the H2 local database
    private void createTakeAwayOrders() {
        List<Customer> customers = customerRepository.findAll();
        List<MenuRestaurant> menus = menuRepository.findAll();

        for (int i = 0; i < 25; i++) {
            TakeAwayOrder order = new TakeAwayOrder();
            order.setId(UUID.randomUUID().toString());
            order.setDate(new Date());
            order.setWaiter(faker.name().fullName());
            order.setPeopleQty(faker.random().nextInt(1, 8));
            order.setTotalPayment(faker.number().randomDouble(2, 10, 200));
            order.setPaid(faker.bool().bool());

            // Set customer
            order.setCustomerTakeAway(customers.get(faker.random().nextInt(customers.size())));

            // Create OrderMenuQty entries
            List<OrderMenuQty> orderMenuQties = new ArrayList<>();
            for (int j = 0; j < faker.random().nextInt(1, 5); j++) {
                OrderMenuQty orderMenuQty = new OrderMenuQty();
                orderMenuQty.setId(UUID.randomUUID().toString());
                orderMenuQty.setOrder(order);
                orderMenuQty.setMenu(menus.get(faker.random().nextInt(menus.size())));
                orderMenuQty.setQuantity(faker.random().nextInt(1, 5));
                orderMenuQties.add(orderMenuQty);
            }
            order.setOrderMenuQties(orderMenuQties);

            takeAwayOrderRepository.save(order);
            orderMenuQtyRepository.saveAll(orderMenuQties);
        }
    }
}*/
