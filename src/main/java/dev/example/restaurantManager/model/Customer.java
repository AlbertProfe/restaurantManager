package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private int age;
    private boolean vipCustomer;
    private boolean deleted;

}