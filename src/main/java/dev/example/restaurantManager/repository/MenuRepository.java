package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;

public interface MenuRepository extends JpaRepository <Menu, String> {


    // 0. Count menus by name
    int countByName(String name);
    // 1. Find menus by name
    ArrayList<Menu> findByName (String name);
    // 2. Find menus by price
    ArrayList<Menu> findByPrice (Double price);
    // 3. Find menus by content
    ArrayList<Menu> findByContent (String content);
    // 4. Find menus by status active
    ArrayList<Menu> findByActive (boolean active);
    // 5. Find menus by containing water
    ArrayList<Menu> findByWater (boolean water);
    // 6. Find menus by price less than a specified value and not deleted
    List<Menu> findByPriceLessThanAndDeletedFalse (int price);
    // 7. Find menus by price more than a specified value and not deleted
    List<Menu> findByPriceMoreThanAndDeletedFalse (int price);


}
