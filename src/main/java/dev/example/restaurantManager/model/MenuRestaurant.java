package dev.example.restaurantManager.model;

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

    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders = new ArrayList<>();

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "menu_restaurant_menu_item",
            joinColumns = @JoinColumn(name = "menu_restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.active = active;
        this.water = water;
        this.orders = new ArrayList<>();
        this.menuItems = new ArrayList<>();
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
        menuItem.getMenus().add(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuRestaurant that = (MenuRestaurant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // We might want to exclude 'orders' from toString() to avoid circular references
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
                ", orders=" + orders + '\'' +
                ", menuItemsCount=" + + (menuItems != null ? menuItems.size() : 0) +
              //  ", menuItems=" + menuItems +
                '}';
    }

}
