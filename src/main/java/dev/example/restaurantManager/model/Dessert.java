package dev.example.restaurantManager.model;

import java.util.List;

public class Dessert extends MenuItem {
    public Dessert(String string, String dish, String s, double v) {
    }

    public Dessert() {
    }

    public Dessert(String id, String name, String description, double price, List<MenuRestaurant> menus) {
        super(id, name, description, price, menus);
    }
}
