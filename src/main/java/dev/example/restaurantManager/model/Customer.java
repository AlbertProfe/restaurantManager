package dev.example.restaurantManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private int age;
    private boolean vipCustomer;
    private boolean deleted;
    
    @OneToMany(mappedBy = "customerMapped", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public Customer(String id, String name, String email, String phoneNumber,
                    int age, boolean vipCustomer, boolean deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.vipCustomer = vipCustomer;
        this.deleted = deleted;
    }

    //method to add
    public void addBooking(Booking booking) {
        this.getBookings().add(booking);
        booking.setCustomerMapped(this);
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                "name: " + name + "\n"  +
                "email: " + email + "\n"  +
                "phoneNumber: " + phoneNumber + "\n"  +
                "age: " + age +"\n"  +
                "vipCustomer: " + vipCustomer
                ;
    }
}