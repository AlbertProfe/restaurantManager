package dev.example.restaurantManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String name;
    private String phoneNumber;
    private int peopleQty;
    private LocalDateTime date;
    private List<TableRestaurant> tableRestaurants;
    private boolean confirmed;

    // Custom toString method (optional, as @Data provides a default toString)
    @Override
    public String toString() {
        return
                "name: " + name + "\n"  +
                "phoneNumber: " + phoneNumber + "\n"  +
                "peopleQty: " + peopleQty +"\n"  +
                "date: " + date +"\n"  +
                "tables: " + tableRestaurants
                ;
    }
}