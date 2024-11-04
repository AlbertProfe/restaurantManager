package dev.example.restaurantManager;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.*;
import jakarta.transaction.Transactional;
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
    @Autowired
    OrderMenuQtyRepository orderMenuQtyRepository;


    List<MenuRestaurant> menus;
    List<OrderRestaurant> orders;

    Faker faker;

    @BeforeEach
    public void createDataAndSave2DBWithoutRelationship() {
        // delete all data
        customerRepository.deleteAll();
        tableRepository.deleteAll();
        orderMenuQtyRepository.deleteAll();
        menuRepository.deleteAll();
        shippingOrderRepository.deleteAll();

        // create always same data
        faker = new Faker(new Random(42));

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
        ShippingOrderRestaurant so1 = new ShippingOrderRestaurant("SO1", new Date(), "John", 2, 0.0, false, (List)null, "123 Main St", "New York", "Mike");
        ShippingOrderRestaurant so2 = new ShippingOrderRestaurant("SO2", new Date(), "Sarah", 1, 0.0, true, (List)null, "456 Elm St", "Los Angeles", "Tom");
        ShippingOrderRestaurant so3 = new ShippingOrderRestaurant("SO3", new Date(), "Emily", 3, 0.0, false, (List)null, "789 Oak St", "Chicago", "Lisa");
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


    private List<OrderMenuQty> getRandomMenuQty(OrderRestaurant orderRestaurant){
        List<OrderMenuQty> menusQty = new ArrayList<>();
        for(MenuRestaurant m:menus){
            if (faker.random().nextInt(0,3) == 0){
                continue;
            }
            OrderMenuQty omq = new OrderMenuQty();
            omq.setOrder(orderRestaurant);
            omq.setMenu(m);
            omq.setQuantity(faker.random().nextInt(1,5));
            menusQty.add(omq);
        }
        return menusQty;
    }



    @Test
    // https://stackoverflow.com/questions/11746499/how-to-solve-the-failed-to-lazily-initialize-a-collection-of-role-hibernate-ex
    @Transactional // (propagation= Propagation.REQUIRED)
    public void createOrderMenuQtyDB() {
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant)orders.get(0);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        so1.setMenus(menusQty);
        // save relationship to DB
        shippingOrderRepository.save(so1);

        // check if shipping order is in DB
        Optional<ShippingOrderRestaurant> found =  shippingOrderRepository.findById(so1.getId());
        assertThat(found).isPresent();
        ShippingOrderRestaurant so1DB = found.get();

        // check if shipping order has all his menus saved to DB
        // this method must be @Transactional because
        // menus in order are fetch.LAZY
        int nMenus = so1DB.getMenus().size();
        for(int i=0;i<nMenus;i++){
            System.out.println(so1DB.getMenus().get(i));
        }
        assertThat(so1DB.getId()).isEqualTo(so1.getId());



    }


    @Test
    public void createOrderManyMenus() {
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant)orders.get(1);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        int nMenus = 0;
        int totalQtyMenus = 0;
        // every menu qty will be greater than 1
        for(OrderMenuQty q:menusQty){
            if(q.getQuantity()<2){
                q.setQuantity(faker.random().nextInt(2,8));
            }
            totalQtyMenus += q.getQuantity();
            nMenus += 1;
        }
        so1.setMenus(menusQty);
        System.out.println("total menus: " + nMenus);
        System.out.println("total qty menus: " + totalQtyMenus);
        shippingOrderRepository.save(so1);

        Optional<ShippingOrderRestaurant> found = shippingOrderRepository.findById(so1.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(so1.getId());
        assertThat(found.get().getMenus().stream()
                .count()
        ).isEqualTo(nMenus);
        assertThat(found.get().getMenus().stream()
                .mapToInt(omq -> omq.getQuantity())
                .sum()
        ).isEqualTo(totalQtyMenus);


    }

    @Test
    public void checkOrderMenuQtyRepository() {
        List<OrderMenuQty> menusQtyDB = orderMenuQtyRepository.findAll();
        // no relationship is on DB
        assertThat(menusQtyDB.size()).isEqualTo(0);

        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant) orders.get(2);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        so1.setMenus(menusQty);
        // save some relationships
        shippingOrderRepository.save(so1);

        menusQtyDB = orderMenuQtyRepository.findAll();

        // check relationships are the same that we assign
        assertThat(menusQtyDB).containsAll(menusQty);

    }

    @Test
    public void createOrderAndDeleteSomeQtyMenus() {
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant) orders.get(0);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        so1.setMenus(menusQty);
        int nMenusOriginal = menusQty.size();
        // save some order with some menus
        shippingOrderRepository.save(so1);


        // get order from DB
        Optional<ShippingOrderRestaurant> found = shippingOrderRepository.findById(so1.getId());
        assertThat(found).isPresent();
        // check order from DB is the same as in memory
        assertThat(found.get().getId()).isEqualTo(so1.getId());
        ShippingOrderRestaurant soDB = found.get();
        // print menu qty from order
        List<OrderMenuQty> menus = soDB.getMenus();
        int nMenus1 = menus.size();
        System.out.println(menus);

        // delete first menu qty from order and save to DB using
        // method removeMenuQty from OrderRestaurant
        // and save to DB
        String id = menus.get(0).getId();
        OrderMenuQty omq = menus.get(0);
        so1.removeMenuQty(omq.getMenu(),omq.getQuantity());
        shippingOrderRepository.save(so1);

        // load the order from DB
        // now should have 1 menu qty less
        found = shippingOrderRepository.findById(so1.getId());
        ShippingOrderRestaurant soDB2 = found.get();
        List<OrderMenuQty> menus2 = soDB2.getMenus();
        int nMenus2 = menus2.size();
        System.out.println(menus2);
        List<OrderMenuQty> menus3 = orderMenuQtyRepository.findAll();
        assertThat(nMenusOriginal).isEqualTo(nMenus1);
        assertThat(nMenusOriginal-1).isEqualTo(nMenus2);



        // https://stackoverflow.com/questions/22688402/delete-not-working-with-jparepository
        // System.out.println("before deleteById");
        // orderMenuQtyRepository.deleteById(id);
        // System.out.println("after deleteById");
        // orderMenuQtyRepository.flush();
//        found = shippingOrderRepository.findById(so1.getId());
//        ShippingOrderRestaurant soDB2 = found.get();
//        List<OrderMenuQty> menus2 =soDB2.getMenus();
//        System.out.println(menus2);
//        List<OrderMenuQty> menus3 = orderMenuQtyRepository.findAll();



//        assertThat(found.get().getMenus().stream()
//                .count()
//        ).isEqualTo(nMenus);
//
//
//        List<OrderMenuQty> menusQtyDB = orderMenuQtyRepository.findAll();
//
//        assertThat(menusQtyDB).containsAll(menusQty);

    }


    @Test
    public void createOrderAndDeleteSomeQtyMenusWithMenuQtyRepository() {
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant) orders.get(0);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        so1.setMenus(menusQty);
        int nMenusOriginal = menusQty.size();
        // save some order with some menus
        shippingOrderRepository.save(so1);

        List<OrderMenuQty> menusQtyDB = orderMenuQtyRepository.findAll();
        String id = menusQtyDB.get(0).getId();
        orderMenuQtyRepository.deleteById(id);
        List<OrderMenuQty> menusQtyDB2 = orderMenuQtyRepository.findAll();
        Optional<ShippingOrderRestaurant> found = shippingOrderRepository.findById(so1.getId());
        ShippingOrderRestaurant soDB2 = found.get();
        int nMenusAfterDelete = soDB2.getMenus().size();

        assertThat(nMenusOriginal-1).isEqualTo(nMenusAfterDelete);

    }


}
