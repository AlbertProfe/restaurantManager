package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.DateAndTime;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EatInOrderRestaurant extends OrderRestaurant {

    private ArrayList<TableRestaurant> tableRestaurants = new ArrayList<>();
    //private TableRestaurant orderedTableRestaurant;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TABLE_RESTAURANT_FK_ID")
    private TableRestaurant tableRestaurantMapped;


    public EatInOrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                double totalPayment, boolean paid, ArrayList<MenuRestaurant> menus,
                                ArrayList<TableRestaurant> tableRestaurants) {
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        this.tableRestaurants = tableRestaurants;
    }

    public EatInOrderRestaurant(String string, DateAndTime date, String character, Integer integer, double v, Boolean aBoolean) {
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Eat In\n" +
                "Tables: " + tableRestaurants.stream().map(TableRestaurant::getName).collect(Collectors.joining(", "));
    }
}
