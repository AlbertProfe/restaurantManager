package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
@Entity
public class MenuItem {

    @Id
    private String id;

    private String name;
    private String description;
    private boolean isSpicy;
    private boolean hasGluten;
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @ManyToMany(mappedBy = "items")
    private ArrayList<MenuRestaurant> menus;

    // Default constructor
    public MenuItem() {
        this.id = UUID.randomUUID().toString();
    }

    // Constructors, getters, and setters
}

