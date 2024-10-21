package dev.example.restaurantManager.model;

<<<<<<< HEAD
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
=======
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
>>>>>>> master

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
<<<<<<< HEAD
@Table (name = "table_restaurant")
=======
>>>>>>> master
public class TableRestaurant {

    @Id
    private String id;
    private String name;
    private String description;
    private int qty;
    private boolean busy;

<<<<<<< HEAD
=======
    @OneToMany(mappedBy = "tableRestaurantMapped", cascade = CascadeType.ALL)
    private ArrayList<Booking> bookings ;


    // we must create a VERY CONCRETE constructor to RUN the OLD tests
    public TableRestaurant(String name, String description , int qty,  boolean busy) {
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

>>>>>>> master

}