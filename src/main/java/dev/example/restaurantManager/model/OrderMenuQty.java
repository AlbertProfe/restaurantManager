package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Data
@Entity
@Table(name = "ORDER_MENU_QTY")
@IdClass(PK_OrderMenuQty.class)
public class OrderMenuQty {
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    private String id;


    @Id
    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName="id", nullable = false)
    // @JoinColumn(name = "order_id")
    private OrderRestaurant order;

    @Id
    @ManyToOne
    @JoinColumn(name="menu_id", referencedColumnName="id", nullable = false)
    // @JoinColumn(name = "menu_id")
    private MenuRestaurant menu;

    private int quantity;


    public PK_OrderMenuQty getId() {
        return new PK_OrderMenuQty(order,menu);
    }

    public void setId(PK_OrderMenuQty id) {
        this.order = id.getOrder();
        this.menu = id.getMenu();
    }


    @Override
    public String toString(){
        return "order_id: " + order.getId() + ", menu_id: " + menu.getId() + ", qty:  " + this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderMenuQty that)) return false;
        // don't check because id it's set in db
        // Objects.equals(id, that.id)
        return quantity == that.quantity && order.getId().equals(that.order.getId()) && menu.getId().equals(that.menu.getId());
    }


}
