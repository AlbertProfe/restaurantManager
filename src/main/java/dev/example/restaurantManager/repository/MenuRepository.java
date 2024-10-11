package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {


}
