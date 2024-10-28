package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TableRestaurant {

    @Id
    private String id;
    private String name;
    private String description;
    private int qty;
    private boolean busy;

    @OneToMany(mappedBy = "tableRestaurantMapped", cascade = CascadeType.ALL,
       fetch = FetchType.EAGER)
    private ArrayList<Booking> bookings ;

    // we must create a VERY CONCRETE constructor to RUN the OLD tests
    public TableRestaurant(String name, String description, int qty, boolean busy) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.busy = busy;
    }

    //method to add
    public void addBooking(Booking booking) {
        if (bookings == null) {
            bookings = new ArrayList<>();
        }
        // Check if the booking is not already in this table's bookings
        if (!bookings.contains(booking)) {
            this.getBookings().add(booking);
        }

        /*
        if (booking.getTableRestaurantMapped() != null)
            booking.getTableRestaurantMapped().getBookings().remove(booking);
        */
        booking.setTableRestaurantMapped(this);
    }

    @Override
    public String toString() {
        return "Table{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", busy=" + busy +
                '}';
    }


}