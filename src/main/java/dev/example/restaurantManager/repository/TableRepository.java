package dev.example.restaurantManager.repository;


import dev.example.restaurantManager.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository <Table, String> {

    // 0. Count tables by name
    int countByName(String name);
    // 1. Find tables by name
    ArrayList<Table> findByName(String name);
    // 2. Find tables by quantity
    ArrayList<Table> FindByQty(int qty);
    // 3. Find tables by busy or not
    List<Table> findByBusy(boolean busy);



}
