package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRestaurantRepository extends JpaRepository<MenuRestaurant, String> {
}
