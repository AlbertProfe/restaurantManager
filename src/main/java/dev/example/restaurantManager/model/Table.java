package dev.example.restaurantManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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