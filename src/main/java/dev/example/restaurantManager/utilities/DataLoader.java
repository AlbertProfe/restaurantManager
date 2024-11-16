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
    private EatInRepository eatInOrderRepository;
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

    private void createTakeAwayOrders() {
    }

    private void createShippingOrders() {
    }

    private void createEatInOrders() {
    }

    private void createOrdersAndAssignMenus() {
    }

    private void createBookingsAndAssignTablesAndCustomers() {
    }

    private void createMenusAndAssignMenuItems() {
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

        // Listas predefinidas para los valores de sideDish, salad y dessert
        List<String> sideDishes = Arrays.asList("Fries", "Rice", "Mashed Potatoes", "Grilled Vegetables", "Garlic Bread");
        List<String> flavors = Arrays.asList("Chocolate", "Vanilla", "Strawberry", "Lemon", "Banana");
        List<String> desserts = Arrays.asList("Chocolate Cake", "Ice Cream", "Apple Pie", "Cheesecake", "Tiramisu");

        // Crear 25 elementos de menú de tipo MainCourse o Dessert
        for (int i = 0; i < 25; i++) {

            // Decidir aleatoriamente si el menú será un MainCourse o un Dessert
            boolean isMainCourse = faker.random().nextBoolean();

            if (isMainCourse) {
                // Crear un MainCourse
                MainCourse mainCourse = new MainCourse(
                        UUID.randomUUID().toString(),
                        faker.food().dish(),
                        faker.food().ingredient() + " " + faker.food().ingredient(),
                        faker.number().randomDouble(2, 5, 30),
                        sideDishes.get(faker.random().nextInt(sideDishes.size())), // Selecciona un side dish aleatorio
                        faker.number().numberBetween(200, 800), // Calorías
                        faker.random().nextBoolean() // Chef Recommendation
                );

                // Guardar el MainCourse
                menuItemRepository.save(mainCourse);

            } else {
                // Crear un Dessert
                SideCourse dessert = new SideCourse(
                        UUID.randomUUID().toString(),
                        desserts.get(faker.random().nextInt(desserts.size())),
                        faker.food().ingredient() + " " + faker.food().ingredient(),
                        faker.number().randomDouble(2, 3, 15),
                        faker.random().nextBoolean(), // Gluten Free
                        flavors.get(faker.random().nextInt(flavors.size())), // Flavor
                        faker.number().numberBetween(1, 10) // Sweetness Level
                );

                // Guardar el Dessert
                menuItemRepository.save(dessert);
            }
        }
    }

}
