package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuItem.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DessertRepository extends JpaRepository<Dessert,String> {

    // to do some test
    Optional<Dessert> findByDescription(String description);


}
