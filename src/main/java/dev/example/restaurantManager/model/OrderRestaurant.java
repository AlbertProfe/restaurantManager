package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDER_RESTAURANT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderRestaurant {

    @Id
    private String id; // Considera usar Long con @GeneratedValue si quieres generarlo automáticamente
    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_menu",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<MenuRestaurant> menus = new ArrayList<>();

    // Constructor actualizado
    public OrderRestaurant(String id, Date date, String waiter, int peopleQty, double totalPayment, boolean paid, List<MenuRestaurant> menus) {
        this.id = id; // Asigna el id
        this.date = date;
        this.waiter = waiter;
        this.peopleQty = peopleQty;
        this.totalPayment = totalPayment;
        this.paid = paid;
        this.menus = menus != null ? menus : new ArrayList<>(); // Asegúrate de inicializar menus
    }

    public void addMenu(MenuRestaurant menu) {
        this.menus.add(menu);
        menu.getOrders().add(this);
    }

    public void removeMenu(MenuRestaurant menu) {
        this.menus.remove(menu);
        menu.getOrders().remove(this);
    }

    @Override
    public String toString() {
        return "OrderRestaurant{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", waiter='" + waiter + '\'' +
                ", peopleQty=" + peopleQty +
                ", totalPayment=" + totalPayment +
                ", paid=" + paid +
                ", menusCount=" + menus.size() +
                ", menus=" + menus +
                '}';
    }
}
