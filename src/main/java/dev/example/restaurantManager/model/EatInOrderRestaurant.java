package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EatInOrderRestaurant extends OrderRestaurant {

    // Relación ManyToOne con TableRestaurant: muchas órdenes pueden pertenecer a una mesa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_restaurant_id", nullable = false)
    @JsonIgnore
    private TableRestaurant tableRestaurant;

    public EatInOrderRestaurant(String id, Date date, String waiter, int peopleQty, double totalPayment, boolean paid, TableRestaurant tableRestaurant, ArrayList<MenuRestaurant> menus) {
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        this.tableRestaurant = tableRestaurant;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Eat In\n" +
                "Table: " + (tableRestaurant != null ? tableRestaurant.getName() : "None");
    }
}
