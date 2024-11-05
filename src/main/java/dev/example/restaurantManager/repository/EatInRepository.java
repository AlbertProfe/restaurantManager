package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EatInRepository extends JpaRepository<EatInOrderRestaurant, String> {
}