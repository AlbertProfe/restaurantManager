package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.awt.*;
import java.util.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
            //    ", menus=" + menus +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return isSpicy == menuItem.isSpicy &&
                hasGluten == menuItem.hasGluten &&
                isAvailable == menuItem.isAvailable &&
                Objects.equals(id, menuItem.id) &&
                Objects.equals(name, menuItem.name) &&
                Objects.equals(description, menuItem.description) &&
                courseType == menuItem.courseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isSpicy, hasGluten, isAvailable, courseType);
    }

}

