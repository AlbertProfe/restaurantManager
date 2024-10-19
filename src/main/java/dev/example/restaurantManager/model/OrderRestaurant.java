package dev.example.restaurantManager.model;

import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderRestaurant {

    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;
    private ArrayList<TableRestaurant> tables;
    private ArrayList<MenuRestaurant> menus;

    @Override
    public String toString() {
        return
                "date: " + date + "\n"  +
                "waiter: " + waiter  + "\n"  +
                "peopleQty: " + peopleQty +"\n"  +
                "totalPayment: " + totalPayment +" euros\n"  +
                "paid: " + paid +"\n"  +
                "Tables quantity: " + tables.size() +"\n"  +
                "table: " + tables +"\n"  +
                "Menus quantity: " + menus.size() +"\n"  +
                "menus: " + menus
                ;
    }

}