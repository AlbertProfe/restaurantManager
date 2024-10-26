package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.MenuRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;

public interface MenuRepository extends JpaRepository <MenuRestaurant, String> {


//    // 0. Count menus by name
//    int countByName(String name);
//    // 1. Find menus by name
//    ArrayList<MenuRestaurant> findByName (String name);
//    // 2. Find menus by price
//    ArrayList<MenuRestaurant> findByPrice (Double price);
//    // 3. Find menus by content
//    ArrayList<MenuRestaurant> findByContent (String content);
//    // 4. Find menus by status active
//    ArrayList<MenuRestaurant> findByActive (boolean active);
//    // 5. Find menus by containing water
//    ArrayList<MenuRestaurant> findByWater (boolean water);
//    // 6. Find menus by price less than a specified value and not deleted
//    List<MenuRestaurant> findByPriceLessThanAndDeletedFalse (int price);
//    // 7. Find menus by price more than a specified value and not deleted
//    List<MenuRestaurant> findByPriceMoreThanAndDeletedFalse (int price);


}
