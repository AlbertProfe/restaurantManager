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

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY
//            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "ORDER_RESTAURANT_MENU",
//            joinColumns = @JoinColumn(name = "ORDER_RESTAURANT_FK_ID"),
//            inverseJoinColumns = @JoinColumn(name = "MENU_RESTAURANT_FK_ID")
//    )

    @OneToMany(mappedBy = "orderRestaurantMapped", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private ArrayList<OrderMenuQty> orderMenuQts;
    //private List<MenuRestaurant> menus = new ArrayList<>();

//    public List<MenuRestaurant> addMenuQty(OrderMenuQty menuQty) {
//            this.orderMenuQts.add(menuQty);
//        menuQty.getQty().add(this);
//        return this.orderMenuQts;
//    }
//
//    public List<OrderMenuQty> removeMenuQty(OrderMenuQty menuQty) {
//            this.orderMenuQts.remove(menuQty);
//        menuQty.getOrders().remove(this);
//        return this.orderMenuQts;
//    }

    @Override
    public String toString() {
        return "OrderRestaurant{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", waiter='" + waiter + '\'' +
                ", peopleQty=" + peopleQty +
                ", totalPayment=" + totalPayment +
                ", paid=" + paid +
                //", menusCount=" + (menus != null ? menus.size() : 0) +
                //", menus=" + menus +
                '}';
    }

}