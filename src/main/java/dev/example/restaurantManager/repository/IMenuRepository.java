package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuRepository extends JpaRepository<Menu, String> {
}
