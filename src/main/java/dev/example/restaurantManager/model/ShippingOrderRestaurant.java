package dev.example.restaurantManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingOrderRestaurant extends OrderRestaurant {

    private String address;
    private String city;
    private String riderName;

    public ShippingOrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                   double totalPayment, boolean paid, ArrayList<MenuRestaurant> menus,
                                   String address, String city, String riderName) {
        super(id, date, waiter, peopleQty, totalPayment, paid, menus);
        this.address = address;
        this.city = city;
        this.riderName = riderName;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Shipping\n" +
                "Address: " + address + "\n" +
                "City: " + city + "\n" +
                "Rider Name: " + riderName;
    }
}
