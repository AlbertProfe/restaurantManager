package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EatInOrderRestaurant extends OrderRestaurant {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TABLE_RESTAURANT_FK_ID")
    private TableRestaurant tableRestaurant;

    // Constructor for EatInOrderRestaurant with all fields
    public EatInOrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                double totalPayment, boolean paid, List<OrderMenuQty> orderMenuQties,
                                TableRestaurant tableRestaurant) {
        super(id, date, waiter, peopleQty, totalPayment, paid);
        this.setOrderMenuQties(orderMenuQties);
        this.tableRestaurant = tableRestaurant;
    }

    @Override
    public double calculateTotalPayment() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Eat In\n" +
                "Table: " + tableRestaurant;
    }
}
