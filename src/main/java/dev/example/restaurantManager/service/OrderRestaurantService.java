package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderRestaurant;

import java.util.List;

public interface OrderRestaurantService {

    List<OrderRestaurant> getAllOrders();
    OrderRestaurant createOrder(OrderRestaurant order);
    OrderRestaurant getOrderById(String id);
    OrderRestaurant updateOrder(String id, OrderRestaurant orderDetails);
    boolean deleteOrder(String id);
    public long countOrders();
}
