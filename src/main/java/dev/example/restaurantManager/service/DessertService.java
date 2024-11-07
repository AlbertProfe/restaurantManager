package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Dessert;
import java.util.List;

public interface DessertService {
    List<Dessert> getAllDesserts();
    Dessert createDessert(Dessert dessert);
    Dessert getDessertById(String id);
    Dessert updateDessert(String id, Dessert dessertDetails);
    boolean deleteDessert(String id);
    long countDesserts();
}
