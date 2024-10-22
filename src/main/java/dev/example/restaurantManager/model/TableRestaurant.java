package dev.example.restaurantManager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.UUID;

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

    @OneToMany(mappedBy = "tableRestaurantMapped", cascade = CascadeType.ALL)
    private ArrayList<Booking> bookings ;

    @OneToMany(mappedBy = "orderedTableRestaurant", cascade = CascadeType.ALL)
    private ArrayList<EatInOrderRestaurant> EatInOrders;

     //we must create a VERY CONCRETE constructor to RUN the OLD tests
    public TableRestaurant(String name, String description , int qty,  boolean busy) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.busy = busy;

    }

    public TableRestaurant(String id, String name, String description, Integer qty, Boolean busy, ArrayList<EatInOrderRestaurant> EatInOrders) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.busy = busy;
        this.EatInOrders = EatInOrders;
    }



    //method to add
    public void addBooking(Booking booking) {
        this.getBookings().add(booking);
        if (booking.getTableRestaurantMapped() != null) booking.getTableRestaurantMapped().getBookings().remove(booking);
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