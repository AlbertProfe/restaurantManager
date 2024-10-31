package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderRestaurant;

import java.util.List;

public interface OrderService {
    long countOrders();

    List<OrderRestaurant> getAllOrders();
    OrderRestaurant getOrderById(String id);
    OrderRestaurant createOrder(OrderRestaurant order);
    OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails);
    boolean deleteOrder(String id);
}
