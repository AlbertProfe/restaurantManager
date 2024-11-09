package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.MenuItem.Dessert;

import java.util.List;

public interface DessertService {
    List<Dessert> getAllDesserts();
    List<Dessert> getAllSugarFreeDesserts();
    Dessert createDessert(Dessert dessert);
    Dessert getDessertById(String id);
    Dessert updateDessert(String id, Dessert dessertDetails);
    boolean deleteDessert(String id);
    long countDesserts();
}
