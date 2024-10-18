package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
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
@Entity

public class EatInOrder extends OrderRestaurant {

    //all tables
    //private ArrayList<TableRestaurant> tableRestaurants = new ArrayList<>();
    private TableRestaurant orderedTableRestaurant;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TABLE_RESTAURANT_FK_ID")
    private TableRestaurant tableRestaurantMapped;

    public EatInOrder(String id, Date date, String waiter, int peopleQty,
                      double totalPayment, boolean paid, ArrayList<Menu> menus,
                      TableRestaurant tableRestaurantMapped) {
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        this.tableRestaurantMapped = tableRestaurantMapped;
    }

    public void setTableRestaurant(TableRestaurant tableRestaurant) {
        this.tableRestaurantMapped = tableRestaurant;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Eat In\n" +
                "Tables: " + tableRestaurantMapped.getName();
    }
}
