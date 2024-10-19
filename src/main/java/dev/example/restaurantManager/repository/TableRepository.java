package dev.example.restaurantManager.repository;


import dev.example.restaurantManager.model.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TableRepository extends JpaRepository <TableRestaurant, String> {

    // 0. Count tables by name
    int countByName(String name);
    // 1. Find tables by name
    ArrayList<TableRestaurant> findByName(String name);
    // 2. Find tables by quantity
    ArrayList<TableRestaurant> FindByQty(int qty);
    // 3. Find tables by busy or not
    List<TableRestaurant> findByBusy(boolean busy);



}
