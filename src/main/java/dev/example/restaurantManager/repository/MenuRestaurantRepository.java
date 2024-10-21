package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRestaurantRepository extends JpaRepository<MenuRestaurant, String> {
}
