package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.ShippingOrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingOrderRepository extends JpaRepository<ShippingOrderRestaurant,String> {}
