package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingOrderRestaurant extends OrderRestaurant {

    private String address;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_SHIPPING_FK_ID")
    private Customer customerShipping;

    // Constructor for ShippingOrderRestaurant with all fields
    public ShippingOrderRestaurant(String id, Date date, String waiter, int peopleQty,
                                   double totalPayment, boolean paid, List<OrderMenuQty> orderMenuQties,
                                   String address, String phoneNumber, Customer customerShipping) {
        super(id, date, waiter, peopleQty, totalPayment, paid);
        this.setOrderMenuQties(orderMenuQties);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.customerShipping = customerShipping;
    }

    @Override
    public double calculateTotalPayment() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Type: Shipping\n" +
                "Address: " + address + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Customer: " + customerShipping;
    }
}