package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders = new ArrayList<>();

    // Many to Many with MenuItems
    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "menu_items",
            joinColumns = @JoinColumn(name = "MENU_FK_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_FK_ID")
    )
    private List<MenuItem> menuItems = new ArrayList<>();

    // Create add method
    public List<MenuItem> addItem(MenuItem item){
        this.menuItems.add(item);
        item.getMenuRestaurants().add(this);
        return this.menuItems;
    }
    // Create remove method
    public List<MenuItem> removeItem(MenuItem item) {
        this.menuItems.remove(item);
        item.getMenuRestaurants().remove(this);
        return this.menuItems;
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
                ", orders=" + orders +
                ", menuItems=" + menuItems +
                '}';
    }

}

