package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@jakarta.persistence.Table(name = "RestaurantTable" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {

    @Id
    private String id;
    private String name;
    private String description;
    private int qty;
    private boolean busy;


}