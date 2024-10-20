package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
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

    @Id
    private String id;
    private ArrayList<TableRestaurant> tableRestaurants = new ArrayList<>();
    //private TableRestaurant orderedTableRestaurant;


    public EatInOrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                double totalPayment, boolean paid, ArrayList<MenuRestaurant> menus,
                                ArrayList<TableRestaurant> tableRestaurants) {
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        this.tableRestaurants = tableRestaurants;
    }
    // EatInOrder @ManyToOne TableRestaurant
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EATINORDER_RESTAURANT_FK_ID")
    private TableRestaurant tableRestaurantMapped;


    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Id: " + id +
                "Type: Eat In\n" +
                "Tables: " + tableRestaurants.stream().map(TableRestaurant::getName).collect(Collectors.joining(", "));
    }
}
