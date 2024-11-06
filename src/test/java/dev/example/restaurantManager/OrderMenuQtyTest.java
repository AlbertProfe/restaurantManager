package dev.example.restaurantManager;

import dev.example.restaurantManager.model.*;
import dev.example.restaurantManager.repository.*;
import dev.example.restaurantManager.utilities.FakeDataLoader;
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
    ShippingOrderRepository shippingOrderRepository;
    @Autowired
    OrderRestaurantRepository orderRepository;
    @Autowired
    MenuRestaurantRepository menuRepository;
    @Autowired
    OrderMenuQtyRepository orderMenuQtyRepository;


//    List<MenuRestaurant> menus;
//    List<OrderRestaurant> orders;

    @Autowired
    FakeDataLoader dataLoader;

    @BeforeEach
    public void createDataAndSave2DBWithoutRelationship() {
        dataLoader.deleteAllData();
        dataLoader.createDataAndSave2DBWithoutRelationship();
//        menus = dataLoader.getMenus();
//        orders = dataLoader.getOrders();
    }

    public List<OrderMenuQty> getRandomMenuQty(OrderRestaurant orderRestaurant){
        List<OrderMenuQty> menusQty = new ArrayList<>();
        for(MenuRestaurant m:menuRepository.findAll()){
            if (dataLoader.getFaker().random().nextInt(0,3) == 0){
                continue;
            }
            OrderMenuQty omq = new OrderMenuQty();
            omq.setOrder(orderRestaurant);
            omq.setMenu(m);
            omq.setQuantity(dataLoader.getFaker().random().nextInt(1,5));
            menusQty.add(omq);
        }
        return menusQty;
    }

    @Test
    // https://stackoverflow.com/questions/11746499/how-to-solve-the-failed-to-lazily-initialize-a-collection-of-role-hibernate-ex
    @Transactional // (propagation= Propagation.REQUIRED)
    public void createOrderMenuQtyDB() {
        List<ShippingOrderRestaurant> orders = shippingOrderRepository.findAll();
        ShippingOrderRestaurant so1 = orders.get(0);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        so1.setMenusQty(menusQty);
        // save relationship to DB
        shippingOrderRepository.save(so1);

        // check if shipping order is in DB
        Optional<ShippingOrderRestaurant> found =  shippingOrderRepository.findById(so1.getId());
        assertThat(found).isPresent();
        ShippingOrderRestaurant so1DB = found.get();

        // check if shipping order has all his menus saved to DB
        // this method must be @Transactional because
        // menus in order are fetch.LAZY
        int nMenus = so1DB.getMenusQty().size();
        for(int i=0;i<nMenus;i++){
            System.out.println(so1DB.getMenusQty().get(i));
        }
        assertThat(so1DB.getId()).isEqualTo(so1.getId());
    }


    @Test
    @Transactional
    public void createOrderManyMenus() {
        List<ShippingOrderRestaurant> orders = shippingOrderRepository.findAll();
        ShippingOrderRestaurant so1 = orders.get(1);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        int nMenus = 0;
        int totalQtyMenus = 0;
        // every menu qty will be greater than 1
        for(OrderMenuQty q:menusQty){
            if(q.getQuantity()<2){
                q.setQuantity(dataLoader.getFaker().random().nextInt(2,8));
            }
            totalQtyMenus += q.getQuantity();
            nMenus += 1;
        }
        so1.setMenusQty(menusQty);
        System.out.println("total menus: " + nMenus);
        System.out.println("total qty menus: " + totalQtyMenus);
        shippingOrderRepository.save(so1);

        Optional<ShippingOrderRestaurant> found = shippingOrderRepository.findById(so1.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(so1.getId());
        assertThat(found.get().getMenusQty().stream()
                .count()
        ).isEqualTo(nMenus);
        assertThat(found.get().getMenusQty().stream()
                .mapToInt(omq -> omq.getQuantity())
                .sum()
        ).isEqualTo(totalQtyMenus);


    }

    @Test
    @Transactional
    public void checkOrderMenuQtyRepository() {
        // no relationship is on DB
        assertThat(orderMenuQtyRepository.findAll().size()).isEqualTo(0);

        List<ShippingOrderRestaurant> orders = shippingOrderRepository.findAll();
        ShippingOrderRestaurant so1 = orders.get(2);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        so1.setMenusQty(menusQty);
        // save some relationships
        shippingOrderRepository.save(so1);


        // check relationships are the same that we assign
        assertThat(orderMenuQtyRepository.findAll()).containsAll(menusQty);

    }

    @Test
    @Transactional
    public void checkOrderMenuQtyRepository2() {
        // no relationship is on DB
        int totalRelationships = 0;
        assertThat(orderMenuQtyRepository.findAll().size()).isEqualTo(totalRelationships);

        List<ShippingOrderRestaurant> orders = shippingOrderRepository.findAll();
        ShippingOrderRestaurant so0 = orders.get(0);
        List<OrderMenuQty> menusQty0 = getRandomMenuQty(so0);
        so0.setMenusQty(menusQty0);
        // save some relationships
        shippingOrderRepository.save(so0);
        totalRelationships += menusQty0.size();
        assertThat(orderMenuQtyRepository.findAll().size()).isEqualTo(totalRelationships);


        ShippingOrderRestaurant so1 = orders.get(1);
        List<OrderMenuQty> menusQty1 = new ArrayList<>();
        for(OrderMenuQty omq:menusQty0){
            OrderMenuQty omqNew = new OrderMenuQty();
            omqNew.setOrder(so1);
            omqNew.setMenu(omq.getMenu());
            omqNew.setQuantity(omq.getQuantity());
            menusQty1.add(omqNew);
        }
        so1.setMenusQty(menusQty1);
        shippingOrderRepository.save(so1);
        totalRelationships += menusQty1.size();
        assertThat(orderMenuQtyRepository.findAll().size()).isEqualTo(totalRelationships);

        ShippingOrderRestaurant so2 = orders.get(2);
        List<OrderMenuQty> menusQty2 = getRandomMenuQty(so2);
        so2.setMenusQty(menusQty2);
        shippingOrderRepository.save(so2);
        totalRelationships += menusQty2.size();
        assertThat(orderMenuQtyRepository.findAll().size()).isEqualTo(totalRelationships);
    }

    @Test
    @Transactional
    public void createOrderAndDeleteSomeQtyMenus() {
        List<ShippingOrderRestaurant> orders = shippingOrderRepository.findAll();
        ShippingOrderRestaurant so1 = orders.get(0);
        List<OrderMenuQty> menusQty = getRandomMenuQty(so1);
        so1.setMenusQty(menusQty);
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
        List<OrderMenuQty> menus = soDB.getMenusQty();
        int nMenus1 = menus.size();
        System.out.println(menus);

        // delete first menu qty from order and save to DB using
        // method removeMenuQty from OrderRestaurant
        // and save to DB
        // String id = menus.get(0).getId();
        OrderMenuQty omq = menus.get(0);
        so1.removeMenuQty(omq.getMenu(),omq.getQuantity());
        shippingOrderRepository.save(so1);

        // load the order from DB
        // now should have 1 menu qty less
        found = shippingOrderRepository.findById(so1.getId());
        ShippingOrderRestaurant soDB2 = found.get();
        List<OrderMenuQty> menus2 = soDB2.getMenusQty();
        int nMenus2 = menus2.size();
        System.out.println(menus2);
        List<OrderMenuQty> menus3 = orderMenuQtyRepository.findAll();
        assertThat(nMenusOriginal).isEqualTo(nMenus1);
        assertThat(nMenusOriginal-1).isEqualTo(nMenus2);

    }

    @Test
    @Transactional
    public void testFindByIdInMenuQtyRepository(){
        // get last order
        ShippingOrderRestaurant so = shippingOrderRepository.findAll().getLast();
        // get some random QtyMenu
        List<OrderMenuQty> menusQty = getRandomMenuQty(so);
        so.setMenusQty(menusQty);
        // save relationships
        shippingOrderRepository.save(so);
        // print all orders relationships
        shippingOrderRepository.findAll().forEach( or -> System.out.println(or.getId() + ": " + or.getMenusQty() ));

        // find from DB first menusQty
        OrderMenuQty omqMemory = menusQty.get(0);
        Optional<OrderMenuQty> foundOmq = orderMenuQtyRepository.findById(omqMemory.getId());
        assertThat(foundOmq).isPresent();
        OrderMenuQty omqDB = foundOmq.get();
        assertThat(omqMemory).isEqualTo(omqDB);
    }

    @Test
    @Transactional
    public void deleteByIdInMenuQtyRepository(){
        // get last order
        ShippingOrderRestaurant so = shippingOrderRepository.findAll().getLast();
        // get some random QtyMenu
        List<OrderMenuQty> menusQty = getRandomMenuQty(so);
        so.setMenusQty(menusQty);
        // save relationships
        shippingOrderRepository.save(so);
        // print all orders relationships
        shippingOrderRepository.findAll().forEach( or -> System.out.println(or.getId() + ": " + or.getMenusQty() ));

        long nOrderMenuQty = menusQty.size();
        System.out.println("total OrderMenuQty: " + nOrderMenuQty);
        // find from DB first menusQty
        OrderMenuQty omqToDelete = menusQty.get(0);
        System.out.println("OrderMenuQty to delete: " + omqToDelete);
        orderMenuQtyRepository.deleteById(omqToDelete.getId());
        orderMenuQtyRepository.flush();

        Optional<OrderMenuQty> found = orderMenuQtyRepository.findById(omqToDelete.getId());
        assertThat(found).isNotPresent();
        assertThat(orderMenuQtyRepository.count()).isEqualTo(nOrderMenuQty-1);
    }

    @Test
    @Transactional
    public void deleteSomeQtyMenusWithMenuQtyRepository() {
        System.out.println("TEST deleteSomeQtyMenusWithMenuQtyRepository BEGIN");
        // check no relationship is stored on DB
        assertThat(orderMenuQtyRepository.findAll().size()).isEqualTo(0);

        // get first order
        List<ShippingOrderRestaurant> orders = shippingOrderRepository.findAll();
        ShippingOrderRestaurant so = orders.get(0);
        // get some random QtyMenu
        List<OrderMenuQty> menusQty = getRandomMenuQty(so);
        so.setMenusQty(menusQty);
        int nMenusOriginal = menusQty.size();
        System.out.println("Printing " + nMenusOriginal + " menu qty from memory");
        menusQty.forEach(System.out::println);
        // save relationship of order with some qty menus
        shippingOrderRepository.save(so);
        System.out.println("After shippingOrderRepository.save");

        // first check: removing one OrderMenuQty with shippingOrderRepository
        OrderMenuQty omq = menusQty.get(0);
        so.removeMenuQty(omq.getMenu(),omq.getQuantity());
        shippingOrderRepository.save(so);
        // get order from db to check if OrderMenuQty is removed
        Optional<ShippingOrderRestaurant> found1 = shippingOrderRepository.findById(so.getId());
        assertThat(found1).isPresent();
        assertThat(found1.get().getMenusQty().size()).isEqualTo(nMenusOriginal-1);
        // insert back OrderMenuQty
        so.addMenuQty(omq.getMenu(),omq.getQuantity());
        shippingOrderRepository.save(so);
        // and check if OrderMenuQty is added
        found1 = shippingOrderRepository.findById(so.getId());
        assertThat(found1).isPresent();
        assertThat(found1.get().getMenusQty().size()).isEqualTo(nMenusOriginal);

        // second check: removing one OrderMenuQty with orderMenuQtyRepository
        List<OrderMenuQty> menusQtyDB = orderMenuQtyRepository.findAll();
        OrderMenuQty omqToDelete = menusQtyDB.get(0);
        System.out.println("Printing menu qty from DB");
        menusQtyDB.forEach(System.out::println);
        System.out.println("Menu qty to delete: " + omqToDelete);
        // As no relationship was stored on DB before this test
        // all relationships on DB belong to the order of this test
        assertThat(menusQtyDB.stream().map(o -> o.getOrder().getId())).allMatch(id -> id.equals(so.getId()) );
        // must be same number of relationships
        assertThat(menusQtyDB.size()).isEqualTo(nMenusOriginal);
        // and now remove one OrderMenuQty with orderMenuQtyRepository
        System.out.println("Before orderMenuQtyRepository.delete");
        // none of the next 2 methods work !!!!
        orderMenuQtyRepository.deleteById(omqToDelete.getId());
        orderMenuQtyRepository.delete(omqToDelete);
        // orderMenuQtyRepository.myDeleteQuery(omqToDelete.getId());
        // no SQL instruction is shown on console !!!
        System.out.println("After orderMenuQtyRepository.delete");
        // this assert will fail
        assertThat(orderMenuQtyRepository.count()).isEqualTo(nMenusOriginal-1);
        System.out.println("TEST deleteSomeQtyMenusWithMenuQtyRepository END");

    }


    @Test
    @Transactional
    public void orderRepositoryAndShippingRepository() {
        System.out.println("TEST orderRepositoryAndShippingRepository BEGIN");
        System.out.println(shippingOrderRepository.findAll());
        String id = shippingOrderRepository.findAll().get(1).getId();
        System.out.println("id from db=" + id);
        String shippingOrderId = "SO2";
        System.out.println("id from db=" + shippingOrderId);
        assertThat(id).isEqualTo(shippingOrderId);
        Optional<ShippingOrderRestaurant> optionalSO = shippingOrderRepository.findById(shippingOrderId);
        assertThat(optionalSO).isPresent();
        ShippingOrderRestaurant so = optionalSO.get();

        Optional<OrderRestaurant> optionalOR = orderRepository.findById(shippingOrderId);
        assertThat(optionalOR).isPresent();
        ShippingOrderRestaurant so2 = (ShippingOrderRestaurant)optionalOR.get();
        assertThat(so).isEqualTo(so2);
        System.out.println("TEST orderRepositoryAndShippingRepository END");
    }


}
