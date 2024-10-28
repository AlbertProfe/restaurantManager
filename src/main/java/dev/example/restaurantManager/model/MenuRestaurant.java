package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MenuRestaurant {

    @Id
    private String id;
    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;

    /*@JsonIgnore
    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders = new ArrayList<>();*/

    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "menu_restaurant_menu_item",
            joinColumns = @JoinColumn(name = "menu_restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<MenuItem> menuItems;

    //PRA04 relationship
    @OneToMany(mappedBy = "menuRestaurantMapped", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private ArrayList<OrderMenuQty> orderMenuQty;

    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.active = active;
        this.water = water;
        this.orderMenuQty = new ArrayList<>();
        this.menuItems = new ArrayList<>();
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
        menuItem.getMenus().add(this);
    }

    public void addOrderMenuQty(OrderMenuQty orderMenuQty) {
        this.orderMenuQty.add(orderMenuQty);
        orderMenuQty.setMenuRestaurantMapped(this);
    }

    @Override
    public String toString() {
        return "MenuRestaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", active=" + active +
                ", water=" + water +
                ", orderMenuQtyCount=" + (orderMenuQty != null ? orderMenuQty.size() : 0) +
                ", menuItemsCount=" + + (menuItems != null ? menuItems.size() : 0) +
              //  ", menuItems=" + menuItems +
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
