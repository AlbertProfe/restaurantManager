package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.awt.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class OrderMenuQty {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ORDER_FK_ID")
    private OrderRestaurant order;

    @ManyToOne
    @JoinColumn(name = "MENU_FK_ID")
    private MenuRestaurant menu;

    private int quantity;


    public OrderMenuQty(OrderRestaurant orderRestaurant, MenuRestaurant menuRestaurant, int i) {

    }
}