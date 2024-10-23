package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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

    @JsonIgnore
    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private List<OrderRestaurant> orders = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "menu_item",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
    private List<MenuItemRestaurant> menuItems = new ArrayList<>();

    public void addMenuItem(MenuItemRestaurant menuItemRestaurant) {
        this.menuItems.add(menuItemRestaurant);
        if (menuItemRestaurant.getMenus() == null) {
            menuItemRestaurant.setMenus(new ArrayList<>());
        }
        if (!menuItemRestaurant.getMenus().contains(this)) {
            menuItemRestaurant.getMenus().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuRestaurant that = (MenuRestaurant) o;
        return active == that.active &&
                water == that.water &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(content, that.content) &&
                Objects.equals(menuItems, that.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, content, active, water, menuItems);
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
                '}';
    }

}

