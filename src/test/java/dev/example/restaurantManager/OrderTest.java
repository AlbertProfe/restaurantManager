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



    }
}
