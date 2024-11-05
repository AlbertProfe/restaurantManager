package dev.example.restaurantManager.repository;



import dev.example.restaurantManager.model.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, String> {

    Optional<TableRestaurant> findByName(String name);

}
