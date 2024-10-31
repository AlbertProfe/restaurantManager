package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.OrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRestaurantRepository extends JpaRepository<OrderRestaurant, String> {
}
