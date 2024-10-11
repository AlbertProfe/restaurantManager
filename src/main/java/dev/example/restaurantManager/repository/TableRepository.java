package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TableRepository extends JpaRepository<TableRestaurant, String> {
}
