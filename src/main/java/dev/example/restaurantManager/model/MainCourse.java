package dev.example.restaurantManager.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MainCourse extends MenuItem {
    private String servingSize;

    public MainCourse(String id, String name, String description, double price, String servingSize) {
        super(id, name, description, price);
        this.servingSize = servingSize;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public void printServingSize() {
        System.out.println("Serving Size: " + servingSize);
    }
}

