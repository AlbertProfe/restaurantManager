package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.OrderMenuQty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuQtyRepository extends JpaRepository<OrderMenuQty,String> {}
