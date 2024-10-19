package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuRestaurant {

    @Id
    private String Id;
    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;

}

