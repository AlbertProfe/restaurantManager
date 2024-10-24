package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class MenuItem {

    @Id
    private String id;

    private String name;
    private String description;
    private boolean isSpicy;
    private boolean hasGluten;
    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    @ManyToMany(mappedBy = "items")
    private ArrayList<MenuRestaurant> menus;

    public void addMenus(List<MenuRestaurant> newMenus){
        if(newMenus== null){
            return;
        }
        if(this.menus == null){
            this.menus = new ArrayList<>();
        }
        for(MenuRestaurant menu:newMenus){
            if(!this.menus.contains(menu)){
                menu.setItems(new ArrayList<>(Arrays.asList(this)));
                this.menus.add(menu);
            }
        }
    }


    // Default constructor
    public MenuItem() {
        this.id = UUID.randomUUID().toString();
    }

    // Constructors, getters, and setters

    @Override
    public boolean equals(Object other){
        if(this==other){
            return true;
        }

        if(other == null || !(other instanceof MenuItem) ){
            return false;
        }

        MenuItem that = (MenuItem)other;

        return this.id.equals(that.id) &&
                this.name.equals(that.name) &&
                this.description.equals(that.description) &&
                this.isSpicy == that.isSpicy &&
                this.hasGluten == that.hasGluten &&
                this.isAvailable == that.isAvailable &&
                this.courseType == that.courseType
        ;
    }

}

