package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.OrderMenuQty;

import java.util.List;

public interface OrderMenuQtyService {
    List<OrderMenuQty> getAllOrderMenuQts();
    List<OrderMenuQty> getAllByOrderId (String orderId);
    OrderMenuQty createOrderMenuQty(OrderMenuQty orderMenuQty);
    OrderMenuQty getOrderMenuQtyById(String id);
    OrderMenuQty updateOrderMenuQty(String id, OrderMenuQty orderMenuQtyDetails);
    boolean deleteOrderMenuQty(String id);
    long countOrderMenuQts();

}
