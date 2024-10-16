package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.EatInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EatInOrderRepository extends JpaRepository<EatInOrder, String> {
}
