package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_RESTAURANT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderRestaurant {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;

    @OneToMany(mappedBy = "orderRestaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderMenuQty> ordersQty = new ArrayList<>();

    public List<OrderMenuQty> addOrderMenuQty(OrderMenuQty orderMenuQty) {
        if(ordersQty == null) {
            ordersQty = new ArrayList<>();
            ordersQty.add(orderMenuQty);
            return ordersQty;
        }else {
            ordersQty.add(orderMenuQty);
            return ordersQty;
        }
    }
    public List<String> getOrderMenuQtyIds() {
        return ordersQty.stream()
                .map(OrderMenuQty::getId)
                .collect(Collectors.toList());
    }

    public void removeOrderMenuQty(OrderMenuQty orderMenuQty) {
        ordersQty.remove(orderMenuQty);
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
                ", se han creado: " + (ordersQty == null ? 0 : ordersQty.size()) + " ordenes" +
                ", las ids son: " + getOrderMenuQtyIds() +
                ", la cantidad de cada menu es:" + ordersQty.stream().collect(Collectors.groupingBy(OrderMenuQty::getMenuName, Collectors.counting())) +
                ", los menus son:" + ordersQty.stream().map(OrderMenuQty::getMenuName).collect(Collectors.toList()) +
                '}';
    }

}