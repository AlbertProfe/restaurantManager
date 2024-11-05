package dev.example.restaurantManager;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.OrderRestaurant;
import dev.example.restaurantManager.model.ShippingOrderRestaurant;
import dev.example.restaurantManager.model.TakeAwayOrder;
import dev.example.restaurantManager.repository.EatInOrderRestaurantRepository;
import dev.example.restaurantManager.repository.ShippingOrderRepository;
import dev.example.restaurantManager.repository.TakeAwayOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderTest {
    @Autowired
    private ShippingOrderRepository shippingOrderRepository;
    @Autowired
    private TakeAwayOrderRepository takeAwayOrderRepository;
    @Autowired
    private EatInOrderRestaurantRepository eatInOrderRepository;

    @Transactional
    @Test
    public void createOrdersTest() {

        List<ShippingOrderRestaurant> shippingOrders = shippingOrderRepository.findAll(PageRequest.of(0, 10)).getContent();
        List<EatInOrderRestaurant> eatInOrders = eatInOrderRepository.findAll(PageRequest.of(0, 10)).getContent();
        List<TakeAwayOrder> takeAwayOrders = takeAwayOrderRepository.findAll(PageRequest.of(0, 10)).getContent();
        // let create a list of orders, with different all kind of orders
        List<OrderRestaurant> orders = new ArrayList<>();
        // add all orders
        orders.addAll(shippingOrders);
        orders.addAll(eatInOrders);
        orders.addAll(takeAwayOrders);
        // print all orders
        orders.forEach(System.out::println);

        assertNotNull(orders);
        assertThat(orders.size()).isEqualTo(30);
        assertThat(orders.get(0)).isInstanceOf(ShippingOrderRestaurant.class);
        //assertThat(orders.get(0)).isNotInstanceOf(OrderRestaurant.class);

    }

    @Transactional
    @Test
    public void testOrderTotalPayment() {

        List<OrderRestaurant> orders = new ArrayList<>();
        orders.addAll(shippingOrderRepository.findAll(PageRequest.of(0, 5)).getContent());
        orders.addAll(eatInOrderRepository.findAll(PageRequest.of(0, 5)).getContent());
        orders.addAll(takeAwayOrderRepository.findAll(PageRequest.of(0, 5)).getContent());

        for (OrderRestaurant order : orders) {
            assertThat(order.getTotalPayment()).isGreaterThan(0.0);
            assertThat(order.getTotalPayment()).isLessThan(200.0);
        }
    }

    @Transactional
    @Test
    public void testOrderDates() {
        List<OrderRestaurant> orders = new ArrayList<>();
        orders.addAll(shippingOrderRepository.findAll(PageRequest.of(0, 5)).getContent());
        orders.addAll(eatInOrderRepository.findAll(PageRequest.of(0, 5)).getContent());
        orders.addAll(takeAwayOrderRepository.findAll(PageRequest.of(0, 5)).getContent());

        for (OrderRestaurant order : orders) {
            assertNotNull(order.getDate());
            assertThat(order.getDate()).isBeforeOrEqualTo(new Date());
        }
    }

    @Transactional
    @Test
    public void testOrderSpecificProperties() {
        ShippingOrderRestaurant shippingOrder = shippingOrderRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
        EatInOrderRestaurant eatInOrder = eatInOrderRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);
        TakeAwayOrder takeAwayOrder = takeAwayOrderRepository.findAll(PageRequest.of(0, 1)).getContent().get(0);

        assertNotNull(shippingOrder.getAddress());
        assertNotNull(eatInOrder.getTableRestaurant());
        assertNotNull(takeAwayOrder.getCustomerTakeAway());
    }
}

