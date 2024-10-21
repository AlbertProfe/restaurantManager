package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.awt.*;
import java.util.*;
import java.util.List;

@AllArgsConstructor
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    // Constructors, getters, and setters
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private boolean isSpicy;
    @Setter
    @Getter
    private boolean hasGluten;
    @Setter
    @Getter
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @ManyToMany(mappedBy = "menuItems")
    private Set<Menu> menus = new HashSet<>();

    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<MenuRestaurant> menuRestaurants = new ArrayList<>();

    // Default constructor
    public MenuItem() {
        this.id = UUID.randomUUID().toString();
    }


}

enum CourseType {
    STARTER,
    MAIN,
    DESSERT
}