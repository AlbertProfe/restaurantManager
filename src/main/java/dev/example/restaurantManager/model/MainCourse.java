package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;

@Entity
public class MainCourse extends MenuItem {

    private String mainCourseSpecificProperty;

    public MainCourse(String id, String name, String description, double price, String mainCourseSpecificProperty) {
        super(id, name, description, price);
        this.mainCourseSpecificProperty = mainCourseSpecificProperty;
    }
}
