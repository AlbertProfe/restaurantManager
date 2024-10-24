package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuItemRepository extends JpaRepository <MenuItem, String> {
    //Optional<MenuItem> findByNameOptional(String name);
    MenuItem findByName(String name);
}
