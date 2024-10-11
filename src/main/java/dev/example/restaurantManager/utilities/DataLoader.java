package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.repository.MenuRepository;
import dev.example.restaurantManager.repository.OrderRepository;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private MenuRepository menuRepository;

    List<TableRestaurant> tables = new ArrayList<>();
    List<MenuRestaurant> menus = new ArrayList<>();

    public void createFakeCustomers() {
        // Check if the database is empty
        if (customerRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));

            int qty = 50;
            // Create and save 100 fake customers
            for (int i = 0; i < qty; i++) {
                Customer customer = new Customer(
                        UUID.randomUUID().toString(),
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().cellPhone(),
                        faker.random().nextInt(18, 130),
                        faker.random().nextBoolean(),
                        faker.random().nextBoolean()

                );
                customerRepository.save(customer);
            }

            System.out.println(qty + " fake customers have been created and saved to the database.");
        }
    }
    public void createFakeTables() {
        // Check if the database is empty
        if (tableRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));
            int qty = 100;
            for(int i = 0; i < qty; i++) {
                TableRestaurant tableRestaurant = new TableRestaurant();
                tableRestaurant.setId(UUID.randomUUID().toString());
                tableRestaurant.setName(faker.name().fullName());
                tableRestaurant.setDescription(faker.lorem().sentence());
                tableRestaurant.setQty(faker.random().nextInt(1, 10));
                tableRestaurant.setBusy(false);

                tableRepository.save(tableRestaurant);
                tables.add(tableRestaurant);
            }
            // Create and save 100 fake tables
            System.out.println(" 100 fake tables have been created and saved to the database");
        }
    }
    public void createFakeMenus(){

        if(menuRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));
            int qty = 10;
            for(int i = 0; i < qty; i++) {
                MenuRestaurant menuRestaurant = new MenuRestaurant();
                menuRestaurant.setId(UUID.randomUUID().toString());
                menuRestaurant.setName(faker.name().fullName());
                menuRestaurant.setPrice(faker.random().nextDouble());
                menuRestaurant.setContent(faker.lorem().sentence());
                menuRestaurant.setActive(faker.random().nextBoolean());
                menuRestaurant.setWater(faker.random().nextBoolean());

                menuRepository.save(menuRestaurant);
                menus.add(menuRestaurant);
            }
            // Create and save 100 fake menus
            System.out.println(" 100 fake menus have been created and saved to the database");
        }
    }
    public void createFakeOrders() {
        if (orderRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));

            int qty = 100;
            for (int i = 0; i < qty; i++) {
                OrderRestaurant orderRestaurant = new OrderRestaurant();
                orderRestaurant.setId(UUID.randomUUID().toString());
                orderRestaurant.setDate(faker.date().between(new Date(0), new Date(System.currentTimeMillis())));
                orderRestaurant.setWaiter(faker.name().fullName());
                orderRestaurant.setPeopleQty(faker.random().nextInt(1, 10));
                orderRestaurant.setTotalPayment(faker.random().nextDouble());
                orderRestaurant.setPaid(faker.random().nextBoolean());

                List<String> tableIds = new ArrayList<>();
                for (int j = 0; j < faker.random().nextInt(1, 10) && j < tables.size(); j++) {
                    TableRestaurant tableRestaurant = tables.get(j);
                    tableIds.add(tableRestaurant.getId());
                }
                orderRestaurant.setTableIds(tableIds);

                List<String> menuIds = new ArrayList<>();
                for (int j = 0; j < faker.random().nextInt(1, 10) && j < menus.size(); j++) {
                    MenuRestaurant menuRestaurant = menus.get(j);
                    menuIds.add(menuRestaurant.getId());
                }
                orderRestaurant.setMenuIds(menuIds);

                orderRepository.save(orderRestaurant);
            }

            System.out.println(" 100 fake orders have been created and saved to the database");
        }
    }
    public void createFakeAllData(){
        createFakeCustomers();
        createFakeTables();
        createFakeMenus();
        createFakeOrders();
    }
}
