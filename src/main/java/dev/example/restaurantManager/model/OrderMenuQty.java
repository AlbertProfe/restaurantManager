package dev.example.restaurantManager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderMenuQty {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private int qty;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_FK_ID")
    private MenuRestaurant menuRestaurantMapped;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_FK_ID")
    private OrderRestaurant orderRestaurantMapped;

    public OrderMenuQty (int qty, MenuRestaurant menuRestaurant, OrderRestaurant orderRestaurant){
        this.qty = qty;
        this.menuRestaurantMapped = menuRestaurant;
        this.orderRestaurantMapped = orderRestaurant;
    }







}
