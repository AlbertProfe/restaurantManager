package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean isSpicy;
    private boolean hasGluten;
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.LAZY)
    private List<MenuRestaurant> menus = new ArrayList<>();

    // Constructor
    public MenuItem(String name, String description, boolean isSpicy, boolean hasGluten, boolean isAvailable, CourseType courseType) {
        this.name = name;
        this.description = description;
        this.isSpicy = isSpicy;
        this.hasGluten = hasGluten;
        this.isAvailable = isAvailable;
        this.courseType = courseType;
    }


    // Constructors, getters, and setters
    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isSpicy=" + isSpicy +
                ", hasGluten=" + hasGluten +
                ", isAvailable=" + isAvailable +
                ", courseType=" + courseType +
                ", menus=" + menus +
                '}';
    }

}

