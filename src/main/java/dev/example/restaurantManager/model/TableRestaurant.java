package dev.example.restaurantManager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
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

    @OneToMany(mappedBy = "tableEatInOrder", cascade = CascadeType.ALL)
    private ArrayList<EatInOrderRestaurant> eatInOrders ;


    public TableRestaurant(String id,String name, String description , int qty,  boolean busy, ArrayList<Booking> bookings, ArrayList<EatInOrderRestaurant> eatInOrders) {
        this.id=id;
        this.name= name;
        this.description=description;
        this.qty=qty;
        this.busy=busy;
        this.bookings = bookings;
        this.eatInOrders = eatInOrders;
    }



    // we must create a VERY CONCRETE constructor to RUN the OLD tests
    public TableRestaurant(String id,String name, String description , int qty,  boolean busy,ArrayList<Booking> bookings) {
        this(id,name, description , qty, busy,bookings,new ArrayList<>());
    }
    // we must create a VERY CONCRETE constructor to RUN the OLD tests
    public TableRestaurant(String name, String description , int qty,  boolean busy) {
        this(name,name, description , qty, busy,new ArrayList<>(),new ArrayList<>());
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