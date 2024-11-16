package dev.example.restaurantManager.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("SIDECOURSE")

public class SideCourse extends MenuItem {

    private boolean isGlutenFree; // indicates if the dessert is gluten-free
    private String flavor;  //Flavor of dessert
    private int sweetnessLevel; //Sweetness Level on a scale from 1 to 10;

    //Constructor that inherits from MenuItem and initializes dessert specific attributes.

    public SideCourse (String id, String name, String description, double price, boolean isGlutenFree){
        super(id, name, description, price); // Calls the constructor of the parent class (MenuItem)
        this.isGlutenFree = isGlutenFree;    // Initializes the gluten-free attribute

    }

    public SideCourse(String string, String s, String s1, double v, Boolean aBoolean, String s2, int i) {
    }

    //method to get a summary of dessert
    public String getSideCourseSummary() {
        return String.format("%s - %s (Price: %.2f, Gluten Free: %b, Flavor: %s, Sweetness Level: %d)",
                getName(), getDescription(), getPrice(), isGlutenFree, flavor, sweetnessLevel);
    }

    public boolean isSuitableForDiet(String dietaryNeed) {
        if(dietaryNeed.equalsIgnoreCase("gluten-free")){
            return isGlutenFree;
        }
        return false; //Default case
    }
}