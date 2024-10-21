package dev.example.restaurantManager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "tableRestaurantMapped", cascade = CascadeType.ALL)
    private ArrayList<Booking> bookings ;

    @OneToMany(mappedBy  = "tableRestaurantMapped", cascade  = CascadeType.ALL)
    private ArrayList<EatInOrderRestaurant> eatInOrderRestaurants;


    // we must create a VERY CONCRETE constructor to RUN the OLD tests
    public TableRestaurant(String name, String description , int qty,  boolean busy) {
    }

    public <E> TableRestaurant(String tr01, String s, String s1, int i, boolean b, ArrayList<E> es) {
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