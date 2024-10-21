package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.OrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRestaurantRepository extends JpaRepository<OrderRestaurant, String> {
}
