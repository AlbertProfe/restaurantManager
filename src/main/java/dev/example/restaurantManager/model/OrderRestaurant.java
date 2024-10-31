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
public abstract class OrderRestaurant {

    @Id
    private String id;
    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderMenuQty> orderMenuQties;


    // Constructor without orderMenuQties
    public OrderRestaurant(String id, Date date, String waiter, int peopleQty, double totalPayment, boolean paid) {
        this.id = id;
        this.date = date;
        this.waiter = waiter;
        this.peopleQty = peopleQty;
        this.totalPayment = totalPayment;
        this.paid = paid;
    }

    public abstract double calculateTotalPayment();

    // Method to add a menu to the order
    public void addMenu(MenuRestaurant menu, int quantity) {
        OrderMenuQty orderMenuQty = new OrderMenuQty();
        orderMenuQty.setOrder(this);
        orderMenuQty.setMenu(menu);
        orderMenuQty.setQuantity(quantity);
        this.orderMenuQties.add(orderMenuQty);
    }

    // Method to remove a menu from the order
    public void removeMenu(MenuRestaurant menu) {
        this.orderMenuQties.removeIf(omq -> omq.getMenu().equals(menu));
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

                '}';
    }

}