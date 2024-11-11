package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderMenuQty;

import java.util.List;

public interface OrderMenuQtyService {

    List<OrderMenuQty> getAllOrderMenuQty();
    OrderMenuQty createOrderMenuQty(OrderMenuQty orderMenuQty);
    OrderMenuQty getOrderMenuQtyById(String id);
    OrderMenuQty updateOrderMenuQty(String id, OrderMenuQty orderMenuQty);
    boolean deleteOrderMenuQty(String id);
    long countOrderMenuQty();

}


