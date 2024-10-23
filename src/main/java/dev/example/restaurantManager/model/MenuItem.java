package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.awt.*;
import java.util.*;
import java.util.List;

@AllArgsConstructor
@Entity
@Data
public class MenuItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;
    private String description;
    private boolean isSpicy;
    private boolean hasGluten;
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

//    @ManyToMany(mappedBy = "menuItems")
//    private Set<Menu> menus = new HashSet<>();

    // Many to many with
    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.LAZY)
    private List<MenuRestaurant> menuRestaurants = new ArrayList<>();

    // Default constructor
    public MenuItem() {
       // this.id = UUID.randomUUID().toString();
    }
// Getters and setters

    @Override
    public String toString() {
        return "MenuRestaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", spicy=" + isSpicy + '\'' +
                ", hasGluten=" + hasGluten +
                ", available=" + isAvailable +
                ", courseType=" + courseType +
                ", menus=" + menuRestaurants +
                '}';
    }

}

