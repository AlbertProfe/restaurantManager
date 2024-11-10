package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class MainCourse extends MenuItem {

    private boolean vegan;
    private boolean glutenFree;

    // Empty constructor for testing via Swagger
    public MainCourse() {
    }

    public MainCourse(String name, String description, double price, boolean vegan, boolean glutenFree) {
        // Call to constructor from superclass
        super(name, description, price);
        // Constructor from subclass
        this.vegan = vegan;
        this.glutenFree = glutenFree;
    }

}
