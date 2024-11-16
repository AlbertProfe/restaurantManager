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

    // One table can have many bookings
    @OneToMany(mappedBy = "tableRestaurantMapped", cascade = CascadeType.ALL)
    private ArrayList<Booking> bookings ;

    // One table can have many eat-in orders
    @OneToMany(mappedBy = "orderedTableRestaurant", cascade = CascadeType.ALL)
    private ArrayList<EatInOrderRestaurant> eatInOrders;

    //Constructor with eatinorders

    public TableRestaurant(String id, String name, String description, int qty, boolean busy, ArrayList<EatInOrderRestaurant> eatInOrders) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.busy = busy;
        this.eatInOrders = eatInOrders;
    }


    // we must create a VERY CONCRETE constructor to RUN the OLD tests
    public TableRestaurant(String name, String description , int qty, boolean busy) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.busy = busy;
    }

    public <E> TableRestaurant(String string, String s, E option, Integer integer, boolean bool) {
    }

    // Méthod tu add orders

    public void addEatInOrder(EatInOrderRestaurant order) {
        this.getEatInOrders().add(order); // Agregar la orden a la lista de órdenes
        if (order.getOrderedTableRestaurant() != null) {
            order.getOrderedTableRestaurant().getEatInOrders().remove(order); // Eliminar de la relación inversa si ya está asignada
        }
        order.setOrderedTableRestaurant(this); // Establecer la mesa como la mesa ordenada en la orden
    }




    //method to add bookings
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