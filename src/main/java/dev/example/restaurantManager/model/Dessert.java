package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;

@Entity
public class Dessert extends MenuItem{

    private boolean sugarFree;

    public Dessert(){

    }

    public Dessert(String id, String name, String description, double price, boolean sugarFree) {
        super(id,name,description,price);
        this.sugarFree = sugarFree;
    }

    public boolean isSugarFree(){
        return sugarFree;
    }

    public void setSugarFree(boolean sugarFree){
        this.sugarFree = sugarFree;
    }

}
