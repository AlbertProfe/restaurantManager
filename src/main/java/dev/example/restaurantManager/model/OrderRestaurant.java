package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
<<<<<<< HEAD

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
=======
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
>>>>>>> master

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
<<<<<<< HEAD
@Table(name = "orders")
public class OrderRestaurant {
=======
@Table(name = "ORDER_RESTAURANT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderRestaurant {

>>>>>>> master
    @Id
    private String id;
    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;

<<<<<<< HEAD
    private List<String> tableIds;
    private List<String> menuIds;

    @Override
    public String toString() {
        return
                "date: " + date + "\n" +
                        "waiter: " + waiter + "\n" +
                        "peopleQty: " + peopleQty + "\n" +
                        "totalPayment: " + totalPayment + " euros\n" +
                        "paid: " + paid + "\n" +
                        "Tables quantity: " + tableIds.size() + "\n" +
                        "table IDs: " + tableIds + "\n" +
                        "Menus quantity: " + menuIds.size() + "\n" +
                        "menu IDs: " + menuIds;
    }
=======
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_menu",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<MenuRestaurant> menus = new ArrayList<>();

    public void addMenu(MenuRestaurant menu) {
        this.menus.add(menu);
        menu.getOrders().add(this);
    }

    public void removeMenu(MenuRestaurant menu) {
        this.menus.remove(menu);
        menu.getOrders().remove(this);
    }

    public List<MenuRestaurant> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuRestaurant> menus) {
        this.menus = menus;
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
                ", menusCount=" + (menus != null ? menus.size() : 0) +
                ", menus=" + menus +
                '}';
    }

>>>>>>> master
}