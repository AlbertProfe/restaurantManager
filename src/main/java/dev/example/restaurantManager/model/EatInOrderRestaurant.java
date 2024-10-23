package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EatInOrderRestaurant extends OrderRestaurant {

    //private ArrayList<TableRestaurant> tableRestaurants = new ArrayList<>();

    @ManyToOne
    private TableRestaurant tableEatInOrder;


    public EatInOrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                double totalPayment, boolean paid, ArrayList<Menu> menus,
                                TableRestaurant tableEatInOrder) {
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        this.tableEatInOrder = tableEatInOrder;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Eat In\n" +
                "Table EatInOrder: " + tableEatInOrder;
//                "Tables: " + tableRestaurants.stream().map(TableRestaurant::getName).collect(Collectors.joining(", "));
    }
}
