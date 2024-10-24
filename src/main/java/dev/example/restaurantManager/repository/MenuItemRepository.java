package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem,String> {}
