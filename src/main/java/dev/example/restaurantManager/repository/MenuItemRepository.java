package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuItemRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItemRestaurant, String> {
}
