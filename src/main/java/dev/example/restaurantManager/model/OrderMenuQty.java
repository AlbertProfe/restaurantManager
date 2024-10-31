package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Data
@Entity
public class OrderMenuQty {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderRestaurant order;

    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private MenuRestaurant menu;

    @Override
    public String toString(){
        return "id=" + id + ", menú: " + menu.getId() + ", qty:  " + this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderMenuQty that)) return false;
        // don't check because id it's set in db
        // Objects.equals(id, that.id)
        return quantity == that.quantity && Objects.equals(order, that.order) && Objects.equals(menu, that.menu);
    }


}
