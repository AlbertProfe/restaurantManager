package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.LAZY)
    private List<MenuRestaurant> menuRestaurants;

//    // Create add method
//    public void addItem(MenuRestaurant menu){
//        this.menuRestaurants.add(menu);
//        menu.getMenuItems().add(this);
//
//    }
//    // Create remove method
//    public void removeItem(MenuRestaurant menu) {
//        this.menuRestaurants.remove(menu);
//        menu.getMenuItems().remove(this);
//    }

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
                //", menus=" + menuRestaurants +
                '}';
    }
    // Updated equals() method to compare all fields except 'menuRestaurants'
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof MenuItem)) return false;
        MenuItem that = (MenuItem) object;
        return isAvailable == that.isAvailable &&
                hasGluten == that.hasGluten &&
                isSpicy == that.isSpicy &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(courseType, that.courseType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isSpicy, hasGluten,isAvailable, courseType);
    }


}