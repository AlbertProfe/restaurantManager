package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.TakeAwayOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeAwayOrderRepository  extends JpaRepository<TakeAwayOrder, String> {
}
