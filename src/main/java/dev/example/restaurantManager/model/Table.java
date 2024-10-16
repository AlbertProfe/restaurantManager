package dev.example.restaurantManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Table implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String Id;
    private String name;
    private String description;
    private int qty;
    private boolean busy;


}