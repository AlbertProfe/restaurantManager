package dev.example.restaurantManager.repository;



import dev.example.restaurantManager.model.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, String> {
}
