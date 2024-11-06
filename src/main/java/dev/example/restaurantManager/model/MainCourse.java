package dev.example.restaurantManager.model;

import java.util.List;

public class MainCourse extends MenuItem {
    public MainCourse(String string, String dish, String s, double v) {
    }

    public MainCourse(String id, String name, String description, double price, List<MenuRestaurant> menus) {
        super(id, name, description, price, menus);
    }

    public MainCourse() {
    }
}
