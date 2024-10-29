package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.OrderMenuQty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuQtyRepository extends JpaRepository <OrderMenuQty, String> {


    List<OrderMenuQty> findAllByOrderId(String orderId);


}
