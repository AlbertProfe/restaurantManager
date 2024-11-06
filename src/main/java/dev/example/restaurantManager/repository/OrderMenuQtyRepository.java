package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.OrderMenuQty;
import dev.example.restaurantManager.model.PK_OrderMenuQty;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderMenuQtyRepository extends JpaRepository<OrderMenuQty, PK_OrderMenuQty> {

    @Modifying
    @Transactional
    @Query("delete from OrderMenuQty omq where omq.id = ?1")
    void myDeleteQuery(PK_OrderMenuQty id);

}
