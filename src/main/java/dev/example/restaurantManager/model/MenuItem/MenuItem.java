package dev.example.restaurantManager.model.MenuItem;

import dev.example.restaurantManager.model.MenuRestaurant;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class MenuItem implements IMenuItem{

    @Id
    private String id;
    private String name;
    private String description;
    private double price;

    @ManyToMany(mappedBy = "menuItems")
    private List<MenuRestaurant> menus;

    public MenuItem(){
    }

    public MenuItem(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "id=" + this.id
            + ", name=" + this.name
            + ", description=" + this.description
            + ", price=" + this.price;
    }

}