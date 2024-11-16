package dev.example.restaurantManager.model;

public interface MenuItemInterface {

    //Method to get the name of the menu item
    String getName();

    //Method to get the description of the menu item
    String getDescription();

    //Method to get the price of the menu item
    double getPrice();

    //Method to get the item type
    String getItemType();

    //Method to provide a summary of the menu item
    String getSummary();
}
