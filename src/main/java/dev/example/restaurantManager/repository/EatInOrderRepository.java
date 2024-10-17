package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.EatInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EatInOrderRepository extends JpaRepository<EatInOrder, String> {
    List<EatInOrder> findByTableRestaurantId(String tableId);
}
