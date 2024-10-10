package dev.example.restaurantManager.service;

import dev.example.restaurantManager.model.Customer;

import java.util.List;

public interface IService<T> {
    List<T> getAllElements();
    T createElement(T element);
    T getElementById(String id);
    T updateElement(String id, T eDetails);
    boolean deleteElement(String id);
    long countElements();
}
