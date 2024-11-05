package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.OrderMenuQty;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderMenuQtyRepository extends JpaRepository<OrderMenuQty,String> {

    @Modifying
    @Transactional
    @Query("delete from OrderMenuQty omq where omq.id = ?1")
    void myDeleteQuery(String id);

}
