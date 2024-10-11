package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderRestaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    long countOrders();

    List<OrderRestaurant> getAllOrders();
    OrderRestaurant getOrderById(String id);
    OrderRestaurant createOrder(OrderRestaurant order);
    OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails);
    boolean deleteOrder(String id);
}
