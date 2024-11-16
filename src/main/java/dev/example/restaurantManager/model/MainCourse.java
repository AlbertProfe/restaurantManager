package dev.example.restaurantManager.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("MAIN_COURSE")
public class MainCourse extends MenuItem {

    private String sideDish; // "chips" or "salad"
    private int calories; // calories content of the main course
    private boolean chefRecommendation;// Indicates if it's a chef's special recommendation

    //constructor inheriting from MenuItem and initializing MainCourse - specific attributes.
    public MainCourse(String id, String name, String description, double price, String sideDish, int calories, boolean chefRecommendation){
        super(id, name, description, price);  // Calls the constructor of MenuItem
        this.sideDish = sideDish;             // Sets the side dish
        this.calories = calories;             // Sets the calories
        this.chefRecommendation = chefRecommendation;  // Marks if it's a chef's recommendation
    }

    public String getFullDescription() {
        return String.format("%s - %s (Price: %.2f, Side Dish: %s, Calories: %d, Chef Recommended: %b)",
                getName(), getDescription(), getPrice(), sideDish, calories, chefRecommendation);
    }

    public boolean isHealthy() {
        return calories < 500;  // Example threshold for healthy
    }

    public int comparePrice(MainCourse other) {
        return Double.compare(this.getPrice(), other.getPrice()); // Compares prices of two main courses
    }
}