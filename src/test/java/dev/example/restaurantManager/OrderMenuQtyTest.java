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
    OrderMenuQtyRepository orderMenuQtyRepository;


    List<MenuRestaurant> menus;
    List<OrderRestaurant> orders;

    @Autowired
    FakeDataLoader dataLoader;

    @BeforeEach
    public void createDataAndSave2DBWithoutRelationship() {
        dataLoader.createDataAndSave2DBWithoutRelationship();
        menus = dataLoader.getMenus();
        orders = dataLoader.getOrders();
    }


    @Test
    // https://stackoverflow.com/questions/11746499/how-to-solve-the-failed-to-lazily-initialize-a-collection-of-role-hibernate-ex
    @Transactional // (propagation= Propagation.REQUIRED)
    public void createOrderMenuQtyDB() {
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant)orders.get(0);
        List<OrderMenuQty> menusQty = dataLoader.getRandomMenuQty(so1);
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
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant)orders.get(1);
        List<OrderMenuQty> menusQty = dataLoader.getRandomMenuQty(so1);
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
    public void checkOrderMenuQtyRepository() {
        List<OrderMenuQty> menusQtyDB = orderMenuQtyRepository.findAll();
        // no relationship is on DB
        assertThat(menusQtyDB.size()).isEqualTo(0);

        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant) orders.get(2);
        List<OrderMenuQty> menusQty = dataLoader.getRandomMenuQty(so1);
        so1.setMenusQty(menusQty);
        // save some relationships
        shippingOrderRepository.save(so1);

        menusQtyDB = orderMenuQtyRepository.findAll();

        // check relationships are the same that we assign
        assertThat(menusQtyDB).containsAll(menusQty);

    }

    @Test
    @Transactional
    public void createOrderAndDeleteSomeQtyMenus() {
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant) orders.get(0);
        List<OrderMenuQty> menusQty = dataLoader.getRandomMenuQty(so1);
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
        String id = menus.get(0).getId();
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
    @Transactional
    public void createOrderAndDeleteSomeQtyMenusWithMenuQtyRepository() {
        ShippingOrderRestaurant so1 = (ShippingOrderRestaurant) orders.get(0);
        List<OrderMenuQty> menusQty = dataLoader.getRandomMenuQty(so1);
        so1.setMenusQty(menusQty);
        int nMenusOriginal = menusQty.size();
        // save some order with some menus
        shippingOrderRepository.save(so1);
        System.out.println("After shippingOrderRepository.save");

        List<OrderMenuQty> menusQtyDB = orderMenuQtyRepository.findAll();
        OrderMenuQty omq = menusQtyDB.get(0);

        System.out.println("Before orderMenuQtyRepository.delete");
        int test = 2;
        if( test==1){
            omq.setMenu(null);
            omq.setOrder(null);
            orderMenuQtyRepository.save(omq);
            orderMenuQtyRepository.delete(omq);
        } else if( test==2) {
            orderMenuQtyRepository.deleteById(omq.getId());
        } else if( test==3) {
            orderMenuQtyRepository.delete(omq);
        } else if( test==4) {
            orderMenuQtyRepository.myDeleteQuery(omq.getId());
        } else if( test==5) {
            String id = menusQtyDB.get(0).getId();
            orderMenuQtyRepository.deleteById(id);
        }
        System.out.println("After orderMenuQtyRepository.delete");
        assertThat(orderMenuQtyRepository.count()).isEqualTo(nMenusOriginal-1);


        Optional<ShippingOrderRestaurant> found = shippingOrderRepository.findById(so1.getId());
        ShippingOrderRestaurant soDB2 = found.get();
        int nMenusAfterDelete = soDB2.getMenusQty().size();

        assertThat(nMenusOriginal-1).isEqualTo(nMenusAfterDelete);

    }


}
