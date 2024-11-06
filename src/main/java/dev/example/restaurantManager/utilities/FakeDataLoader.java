package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class FakeDataLoader {

    @Autowired
    private MenuRestaurantRepository menuRepository;
    @Autowired
    private ShippingOrderRepository shippingOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TableRestaurantRepository tableRepository;
    @Autowired
    private OrderMenuQtyRepository orderMenuQtyRepository;


    private List<MenuRestaurant> menus;
    private List<OrderRestaurant> orders;

    private Faker faker;

    public List<MenuRestaurant> getMenus(){
        return menus;
    }

    public List<OrderRestaurant> getOrders(){
        return orders;
    }

    public Faker getFaker(){
        return faker;
    }


    private void createFakerIfNotExists(){
        if(faker!=null){
            return;
        }

        // create always same data
        faker = new Faker(new Random(42));
    }

    public void deleteAllData(){
        System.out.println("Delete all data");
        // delete all data
        customerRepository.deleteAll();
        tableRepository.deleteAll();
        orderMenuQtyRepository.deleteAll();
        menuRepository.deleteAll();
        shippingOrderRepository.deleteAll();
    }

    public void createDataAndSave2DBWithoutRelationship(){
        createFakerIfNotExists();

        System.out.println("Before creating data");

        System.out.println("Before creating Customers");
        // Create sample customers
        Customer customer1 = new Customer("C1", "John", "john@email.com", "123-456-7890", 30, false, false);
        Customer customer3 = new Customer("C3", "Emily", "emily@email.com", "345-678-9012", 35, false, false);
        Customer customer5 = new Customer("C5", "Anna", "anna@email.com", "567-890-1234", 28, false, false);
        customerRepository.save(customer1);
        customerRepository.save(customer3);
        customerRepository.save(customer5);
        System.out.println("after creating Customers");

        // Create sample tables
        System.out.println("before creating Tables");
        TableRestaurant table1 = new TableRestaurant("T1", "Window Table", 4, false);
        TableRestaurant table2 = new TableRestaurant("T2", "Corner Table", 2, true);
        table1.setId("T01");
        table2.setId("T02");
        tableRepository.save(table1);
        tableRepository.save(table2);
        System.out.println("after creating Tables");


        // Create sample menus
        System.out.println("before creating Menus");
        MenuRestaurant menu1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true);
        MenuRestaurant menu2 = new MenuRestaurant("M02","Pizza Menu", 12.99, "Pizza and salad", true, false);
        MenuRestaurant menu3 = new MenuRestaurant("M03","Salad Menu", 8.99, "Mixed salad and dressing", true, true);
        menus = new ArrayList<>(Arrays.asList(menu1,menu2,menu3));
        menuRepository.save(menu1);
        menuRepository.save(menu2);
        menuRepository.save(menu3);
        System.out.println("after creating Menus");

        // Create 3 ShippingOrder objects
        System.out.println("before creating ShippingOrderRestaurants");
        ShippingOrderRestaurant so1 = new ShippingOrderRestaurant("SO1", new Date(), "John", 2, 0.0, false, (List)null, "123 Main St", "New York", "Mike");
        ShippingOrderRestaurant so2 = new ShippingOrderRestaurant("SO2", new Date(), "Sarah", 1, 0.0, true, (List)null, "456 Elm St", "Los Angeles", "Tom");
        ShippingOrderRestaurant so3 = new ShippingOrderRestaurant("SO3", new Date(), "Emily", 3, 0.0, false, (List)null, "789 Oak St", "Chicago", "Lisa");
        orders = new ArrayList<>(Arrays.asList(so1,so2,so3));
        shippingOrderRepository.save(so1);
        shippingOrderRepository.save(so2);
        shippingOrderRepository.save(so3);
        System.out.println("after creating ShippingOrderRestaurants");

        System.out.println("after creating data");
    }

    public List<OrderMenuQty> getRandomMenuQty(OrderRestaurant orderRestaurant){
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

    public String createSomeRelations(){
        ShippingOrderRestaurant so = shippingOrderRepository.findAll().getFirst();
        String idOrder = so.getId();
        List<OrderMenuQty> menusQty = new ArrayList<>();
        for(MenuRestaurant m:menuRepository.findAll()){
            if (faker.random().nextInt(0,3) == 0){
                continue;
            }
            OrderMenuQty omq = new OrderMenuQty();
            omq.setOrder(so);
            omq.setMenu(m);
            omq.setQuantity(faker.random().nextInt(1,5));
            menusQty.add(omq);
        }
        System.out.println("before saving relationship ShippingOrderRestaurant <-> OrderMenusQqy");
        so.setMenusQty(menusQty);
        shippingOrderRepository.save(so);
        System.out.println("after saving relationship ShippingOrderRestaurant <-> OrderMenusQqy");

//        ShippingOrderRestaurant so1DB = shippingOrderRepository.findById(idOrder).orElse(null);
//        System.out.println(menusQty.size() + "=" + so1DB.getMenusQty().size() );

        return idOrder;
    }


    public void createSomeRelationsOld(){
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant) orders.get(0);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        so1.setMenusQty(menusQty);
        System.out.println("before saving relationship ShippingOrderRestaurant <-> OrderMenusQqy");
//        shippingOrderRepository.save(so1);
//        System.out.println("after saving relationship ShippingOrderRestaurant <-> OrderMenusQqy");
//
//
//        System.out.println("check saving relationship ShippingOrderRestaurant <-> OrderMenusQqy");
        ShippingOrderRestaurant so1DB = shippingOrderRepository.findById(so1.getId()).orElse(null);
        System.out.println(menusQty.size() + "=" + so1DB.getMenusQty().size() );
    }


}
