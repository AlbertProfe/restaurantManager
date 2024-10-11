package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<RestaurantTable, String> {

    Optional<RestaurantTable> findByName(String name);

}
