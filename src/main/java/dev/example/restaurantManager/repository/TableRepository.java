package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, String>{
}
