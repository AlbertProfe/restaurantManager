package dev.example.restaurantManager.model.MenuItem;

import jakarta.persistence.Entity;

@Entity
public class MainCourse extends MenuItem{

    private boolean vegan;

    public MainCourse(){

    }

    public MainCourse(String id, String name, String description, double price, boolean vegan) {
        super(id,name,description,price);
        this.vegan = vegan;
    }

    public boolean isVegan(){
        return vegan;
    }

    public void setVegan(boolean vegan){
        this.vegan = vegan;
    }

}
