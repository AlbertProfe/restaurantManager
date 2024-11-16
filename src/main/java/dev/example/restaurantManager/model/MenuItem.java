package dev.example.restaurantManager.model;

import dev.example.restaurantManager.model.interfaces.IMenuItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)

public abstract class  MenuItem implements MenuItemInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String description;
    private double price;
    @ManyToMany(mappedBy = "menuItems")
    private List<MenuRestaurant> menus;
    public MenuItem(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getItemType() {
        // You can return the class type as the item type
        return this.getClass().getSimpleName();
    }
    @Override
    public String getSummary() {
        return String.format("%s - %s (Price: %.2f)", name, description, price);
    }
}