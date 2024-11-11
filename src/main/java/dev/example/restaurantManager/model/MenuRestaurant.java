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
@NoArgsConstructor
@Entity
public class MenuRestaurant {

    @Id
    private String id;
    private String name;
    private Double price;
    private String content;
    private boolean active;
    private boolean water;


    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "menu_menuItem",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "menuItem_id")
    )
    private List<MenuItem> menuItems = new ArrayList<>();

    //@ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    //private List<OrderRestaurant> orders = new ArrayList<>();


    public MenuRestaurant(String id, String name, Double price, String content, boolean active, boolean water, List<MenuItem> menuItems) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.active = active;
        this.water = water;
        this.menuItems = menuItems != null ? new ArrayList<>(menuItems) : new ArrayList<>();
    }

    // Method para agregar un MenuItem a un MenuRestaurant
    public void addMenuItem(MenuItem menuItem) {
        this.getMenuItems().add(menuItem);
        if (!menuItem.getMenus().contains(this)) {
            menuItem.getMenus().add(this);
        }
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
                ", menuItems=" + menuItems +  // Agregando la lista de items
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Compara la referencia
        if (!(o instanceof MenuRestaurant)) return false;  // Comprueba si el objeto es del mismo tipo
        MenuRestaurant that = (MenuRestaurant) o;  // Cast al objeto de comparación
        return active == that.active &&
                water == that.water &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(content, that.content);  // Compara todos los atributos relevantes
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, content, active, water);  // Genera un hashcode basado en los mismos atributos
    }

}