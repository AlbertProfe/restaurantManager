package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

    @OneToMany(mappedBy = "orderMapped", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMenuQty> orderMenuQtyList = new ArrayList<>();

    // add menus
    public void addMenu(MenuRestaurant menu, int quantity) {
        OrderMenuQty orderMenuQty = new OrderMenuQty();
        orderMenuQty.setOrderMapped(this);
        orderMenuQty.setMenuMapped(menu);
        orderMenuQty.setQuantity(quantity);
        this.orderMenuQtyList.add(orderMenuQty);
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
                    ", orderMenuQtyList=" + orderMenuQtyList +
                    '}';
        }

}