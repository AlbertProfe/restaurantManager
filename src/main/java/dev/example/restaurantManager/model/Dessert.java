package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class Dessert extends MenuItem {

    private boolean lactoseFree;
    private boolean glutenFree;

    public Dessert(String name, String description, double price, boolean lactoseFree, boolean glutenFree) {
        // Call to constructor from superclass
        super(name, description, price);
        // Constructor from subclass
        this.lactoseFree = lactoseFree;
        this.glutenFree = glutenFree;
    }

}
