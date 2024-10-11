package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderRestaurant {
    @Id
    private String id;
    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;

    private List<String> tableIds;
    private List<String> menuIds;

    @Override
    public String toString() {
        return
                "date: " + date + "\n" +
                        "waiter: " + waiter + "\n" +
                        "peopleQty: " + peopleQty + "\n" +
                        "totalPayment: " + totalPayment + " euros\n" +
                        "paid: " + paid + "\n" +
                        "Tables quantity: " + tableIds.size() + "\n" +
                        "table IDs: " + tableIds + "\n" +
                        "Menus quantity: " + menuIds.size() + "\n" +
                        "menu IDs: " + menuIds;
    }
}