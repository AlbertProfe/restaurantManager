package dev.example.restaurantManager.model;

public interface IMenuItem {

    String getId();

    String getName();

    String getDescription();

    double getPrice();

    void setId(String id);

    void setName(String name);

    void setDescription(String description);

    void setPrice(double price);
}
