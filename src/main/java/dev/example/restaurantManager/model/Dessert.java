package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;

@Entity
public class Dessert extends MenuItem {
    private String dessertSpecificProperty;

    public Dessert(String id, String name, String description, double price, String dessertSpecificProperty) {
        super(id, name, description, price);
        this.dessertSpecificProperty = dessertSpecificProperty;
    }
}
