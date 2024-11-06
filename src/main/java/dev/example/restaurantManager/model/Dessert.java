package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Dessert extends MenuItem {

    private boolean lactoseFree;
    private boolean glutenFree;


}
