package dev.example.restaurantManager.model;

import dev.example.restaurantManager.model.MenuItem.MenuItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuRestaurant  {

    @Id
    private String id;
    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMenuQty> orderMenuQties;

    // Constructor without orderMenuQties
    public MenuRestaurant(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    @ManyToMany
    @JoinTable(
            name = "MENU_RESTAURANT_MENU_ITEM",
            joinColumns = @JoinColumn(name = "MENU_RESTAURANT_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "MENU_ITEM_ID_FK")
    )
    private List<MenuItem> menuItems;

    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.active = active;
        this.water = water;
    }

    //We  might want to exclude 'orders' from toString()
    // to avoid circular references
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

    // Updated equals() method to compare all fields except 'orders'
    // to avoid circular references
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuRestaurant)) return false;
        MenuRestaurant that = (MenuRestaurant) o;
        return active == that.active &&
                water == that.water &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(content, that.content);
    }

    // Updated hashCode() method to include all fields except 'orders'
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, content, active, water);
    }

}

