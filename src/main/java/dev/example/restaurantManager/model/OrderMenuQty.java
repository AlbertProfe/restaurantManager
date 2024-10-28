package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.criteria.Order;
import org.hibernate.annotations.GenericGenerator;

import java.awt.*;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuQty {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderRestaurant orderRestaurantMapped;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuRestaurant menuRestaurantMapped;

    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderMenuQty)) return false;
        OrderMenuQty that = (OrderMenuQty) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(orderRestaurantMapped, that.orderRestaurantMapped) &&
                Objects.equals(menuRestaurantMapped, that.menuRestaurantMapped);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderRestaurantMapped, menuRestaurantMapped, quantity);
    }

}
