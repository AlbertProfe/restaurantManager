package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Dessert extends MenuItem {
    private boolean toShare;

    public Dessert(String id, String name, String description, double price, boolean toShare) {
        super(id, name, description, price);
        this.toShare = false;
    }

    public boolean getToShare() {
        return toShare;
    }

    public void setToShare(boolean toShare) {
        this.toShare = toShare;
    }

    // Example method specific to Dessert
    public void isToShare() {
        System.out.println("Dessert to share? " + toShare);
    }
}

