package dev.example.restaurantManager;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import dev.example.restaurantManager.repository.ShippingOrderRepository;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import dev.example.restaurantManager.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class OrderMenuQtyTest {

    @Autowired
    MenuRestaurantRepository menuRepository;
    @Autowired
    ShippingOrderRepository shippingOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TableRestaurantRepository tableRepository;


    List<MenuRestaurant> menus;
    List<OrderRestaurant> orders;

    Faker faker;

    @BeforeEach
    public void createDataAndSave2DB() {
        faker = new Faker();

        // Create sample customers
        Customer customer1 = new Customer("C1", "John", "john@email.com", "123-456-7890", 30, false, false);
        Customer customer3 = new Customer("C3", "Emily", "emily@email.com", "345-678-9012", 35, false, false);
        Customer customer5 = new Customer("C5", "Anna", "anna@email.com", "567-890-1234", 28, false, false);
        customerRepository.save(customer1);
        customerRepository.save(customer3);
        customerRepository.save(customer5);

        // Create sample tables
        TableRestaurant table1 = new TableRestaurant("T1", "Window Table", 4, false);
        TableRestaurant table2 = new TableRestaurant("T2", "Corner Table", 2, true);
        table1.setId("T01");
        table2.setId("T02");
        tableRepository.save(table1);
        tableRepository.save(table2);


        // Create sample menus
        MenuRestaurant menu1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
        MenuRestaurant menu2 = new MenuRestaurant("M02","Pizza Menu", 12.99, "Pizza and salad", true, false);
        MenuRestaurant menu3 = new MenuRestaurant("M03","Salad Menu", 8.99, "Mixed salad and dressing", true, true);
        menus = new ArrayList<>(Arrays.asList(menu1,menu2,menu3));
        menuRepository.save(menu1);
        menuRepository.save(menu2);
        menuRepository.save(menu3);

        // Create 3 ShippingOrder objects
        ShippingOrderRestaurant so1 = new ShippingOrderRestaurant("SO1", new Date(), "John", 2, 0.0, false, null, "123 Main St", "New York", "Mike");
        ShippingOrderRestaurant so2 = new ShippingOrderRestaurant("SO2", new Date(), "Sarah", 1, 0.0, true, null, "456 Elm St", "Los Angeles", "Tom");
        ShippingOrderRestaurant so3 = new ShippingOrderRestaurant("SO3", new Date(), "Emily", 3, 0.0, false, null, "789 Oak St", "Chicago", "Lisa");
        orders = new ArrayList<>(Arrays.asList(so1,so2,so3));
        shippingOrderRepository.save(so1);
        shippingOrderRepository.save(so2);
        shippingOrderRepository.save(so3);

//        // Create 3 EatInOrder objects
//        EatInOrderRestaurant eo1 = new EatInOrderRestaurant("EO1", new Date(), "David", 4, 0.0, true, null, new ArrayList<>(Arrays.asList(table1)));
//        EatInOrderRestaurant eo2 = new EatInOrderRestaurant("EO2", new Date(), "Anna", 2, 0.0, false, null, new ArrayList<>(Arrays.asList(table2)));
//        EatInOrderRestaurant eo3 = new EatInOrderRestaurant("EO3", new Date(), "Mark", 6, 0.0, true, null, new ArrayList<>(Arrays.asList(table1, table2)));
//        orders.addAll(Arrays.asList(eo1,eo2,eo3));
//
//        // Create 3 TakeAwayOrder objects
//        TakeAwayOrder to1 = new TakeAwayOrder("TO1", new Date(), "Alice", 1, 0.0, true, null, customer1 );
//        TakeAwayOrder to2 = new TakeAwayOrder("TO2", new Date(), "Bob", 2, 0.0, false, null, customer3 );
//        TakeAwayOrder to3 = new TakeAwayOrder("TO3", new Date(), "Charlie", 3, 0.0, true, null, customer5);
//        orders.addAll(Arrays.asList(to1,to2,to3));
    }






    @Test
    public void createOrderMenuQtyDB() {
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant)orders.get(0);
        List<OrderMenuQty> menusQty = new ArrayList<>();
        for(MenuRestaurant m:menus){
            if (faker.random().nextInt(0,3) == 0){
                continue;
            }
            OrderMenuQty omq = new OrderMenuQty();
            omq.setOrder(so1);
            omq.setMenu(m);
            omq.setQuantity(faker.random().nextInt(1,5));
            menusQty.add(omq);
        }
        so1.setMenus(menusQty);
        shippingOrderRepository.save(so1);

        Optional<ShippingOrderRestaurant> found =  shippingOrderRepository.findById(so1.getId());
        assertThat(found).isPresent();
        ShippingOrderRestaurant so1DB = found.get();
        int nMenus = so1DB.getMenus().size();
        for(int i=0;i<nMenus;i++){
            System.out.println(so1DB.getMenus().get(i));
        }
        assertThat(so1DB.getId()).isEqualTo(so1.getId());



    }




}
