package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
=======
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
>>>>>>> master

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
<<<<<<< HEAD
@Table(name = "menus")
public class MenuRestaurant {

=======
public class MenuRestaurant  {
>>>>>>> master

    @Id
    private String id;
    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;

<<<<<<< HEAD
=======
    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders = new ArrayList<>();

    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.active = active;
        this.water = water;
    }

    //We  might want to exclude 'orders' from toString() to avoid circular references
    @Override
    public String toString() {
        return "MenuRestaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", active=" + active +
                ", water=" + water +
                '}';
    }

>>>>>>> master
}

