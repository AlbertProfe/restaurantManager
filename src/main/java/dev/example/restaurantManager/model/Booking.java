package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private int peopleQty;
    private Date date;
    private boolean confirmed;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TABLE_RESTAURANT_FK_ID")
    private TableRestaurant tableRestaurantMapped;

    // Custom toString method (optional, as @Data provides a default toString)
    @Override
    public String toString() {
        return
                "name: " + name + "\n"  +
                "phoneNumber: " + phoneNumber + "\n"  +
                "peopleQty: " + peopleQty +"\n"  +
                "date: " + date +"\n"  +
                "table: " + tableRestaurantMapped
                ;
    }
}