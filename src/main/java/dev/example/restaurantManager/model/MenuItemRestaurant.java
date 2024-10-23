package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.example.restaurantManager.enums.CourseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@Entity
@AllArgsConstructor
public class MenuItemRestaurant {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String description;
    private boolean isSpicy;
    private boolean hasGluten;
    private boolean isAvailable;
    private double price;
    @JsonIgnore
    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.EAGER)
    private List<MenuRestaurant> menus = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private CourseType courseType;


    public MenuItemRestaurant() {
        this.id = UUID.randomUUID().toString();
    }

    public void addMenuRestaurant(MenuRestaurant menuRestaurant) {
        this.menus.add(menuRestaurant);
        if (!menuRestaurant.getMenuItems().contains(this)) {
            menuRestaurant.getMenuItems().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemRestaurant)) return false;
        MenuItemRestaurant that = (MenuItemRestaurant) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(isSpicy, that.isSpicy) &&
                Objects.equals(hasGluten, that.hasGluten) &&
                Objects.equals(isAvailable, that.isAvailable) &&
                Objects.equals(price, that.price) &&
                courseType == that.courseType &&
                Objects.equals(menus, that.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isSpicy, hasGluten, isAvailable, price, courseType, menus);
    }

    @Override
    public String toString() {
        return "MenuItemRestaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isSpicy=" + isSpicy +
                ", hasGluten=" + hasGluten +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                ", courseType=" + courseType +
                ", menus=" + menus +
                '}';
    }
}
