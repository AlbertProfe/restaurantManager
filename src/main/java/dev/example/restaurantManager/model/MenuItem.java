package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private CourseEnum courseType;

    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MenuRestaurant> menus = new ArrayList<>();

    public boolean hasGluten() {
        return hasGluten;
    }

    // method para agregar un MenuRestaurant a un MenuItem
    public void addMenu(MenuRestaurant menuRestaurant) {
        this.getMenus().add(menuRestaurant);
        if (!menuRestaurant.getMenuItems().contains(this)) {
            menuRestaurant.getMenuItems().add(this);
        }
    }
    @Override
    public String toString() {
        return "MenuItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isSpicy=" + isSpicy +
                ", hasGluten=" + hasGluten +
                ", isAvailable=" + isAvailable +
                ", courseType=" + courseType +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem that = (MenuItem) o;
        return isSpicy == that.isSpicy &&
                hasGluten == that.hasGluten &&
                isAvailable == that.isAvailable &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                courseType == that.courseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isSpicy, hasGluten, isAvailable, courseType);
    }
}