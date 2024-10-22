package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EatInOrderRestaurant extends OrderRestaurant {

    private ArrayList<TableRestaurant> tableRestaurants = new ArrayList<>();
    //private TableRestaurant orderedTableRestaurant;


    public EatInOrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                double totalPayment, boolean paid, ArrayList<MenuRestaurant> menus,
                                ArrayList<TableRestaurant> tableRestaurants) {
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        this.tableRestaurants = tableRestaurants;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Eat In\n" +
                "Tables: " + tableRestaurants.stream().map(TableRestaurant::getName).collect(Collectors.joining(", "));
    }
}
