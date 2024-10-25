package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
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

    // Many to Many with Orders
    @JsonIgnore
    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders;

    // Many to Many with MenuItems
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "menu_items",
            joinColumns = @JoinColumn(name = "MENU_FK_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_FK_ID")
    )
    private List<MenuItem> menuItems = new ArrayList<>();

    // Create add method
    public void addItem(MenuItem item){
        this.menuItems.add(item);
        item.getMenuRestaurants().add(this);

    }
    // Create remove method
    public void removeItem(MenuItem item) {
        this.menuItems.remove(item);
        item.getMenuRestaurants().remove(this);

    }

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
                ", ordersCount=" + (orders != null ? orders.size() : 0) +
                //", orders=" + orders +
                ", menuItems=" + menuItems +
                '}';
    }

    // Updated equals() method to compare all fields except 'order'
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof MenuRestaurant)) return false;
        MenuRestaurant that = (MenuRestaurant) object;
        return active == that.active &&
                water == that.water &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, content, active, water);
    }

}

