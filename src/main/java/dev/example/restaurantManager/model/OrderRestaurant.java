package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_RESTAURANT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderRestaurant {

    @Id
    private String id;
    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;

    /*@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ORDER_RESTAURANT_MENU",
            joinColumns = @JoinColumn(name = "ORDER_RESTAURANT_FK_ID"),
            inverseJoinColumns = @JoinColumn(name = "MENU_RESTAURANT_FK_ID")
    )
    private List<MenuRestaurant> menus = new ArrayList<>();*/

    @OneToMany(mappedBy = "orderRestaurantMapped", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private ArrayList<OrderMenuQty> orderMenuQty;

    public void addOrderMenuQty(OrderMenuQty orderMenuQty) {
        this.orderMenuQty.add(orderMenuQty);
        orderMenuQty.setOrderRestaurantMapped(this); // Set the back reference
    }

    public void removeOrderMenuQty(OrderMenuQty orderMenuQty) {
        this.orderMenuQty.remove(orderMenuQty);
        orderMenuQty.setOrderRestaurantMapped(null); // Clear the back reference
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
                ", orderMenuQtyCount=" + (orderMenuQty != null ? orderMenuQty.size() : 0) +
                ", orderMenuQty=" + orderMenuQty +
                '}';
    }

}