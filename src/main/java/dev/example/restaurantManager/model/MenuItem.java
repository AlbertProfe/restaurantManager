package dev.example.restaurantManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// Generating object via Swagger it will be needed the field type "dessert" or "mainCourse" for instance each subclass
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dessert.class, name = "dessert"),
        @JsonSubTypes.Type(value = MainCourse.class, name = "mainCourse")
})


public abstract class MenuItem implements IMenuItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String description;
    private double price;

    // Ignoring the List wrapper to focus solely on subclass instances when retrieving all objects via Swagger
    @JsonIgnore
    @ManyToMany(mappedBy = "menuItems")
    private List<MenuRestaurant> menus;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}