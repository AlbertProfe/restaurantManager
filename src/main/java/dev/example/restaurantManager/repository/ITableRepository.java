package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITableRepository extends JpaRepository<Table, String> {
}
