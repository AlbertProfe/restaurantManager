
package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EatInOrderRestaurantRepository extends JpaRepository<EatInOrderRestaurant, String> {
}

