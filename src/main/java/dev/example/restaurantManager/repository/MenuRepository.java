package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, String> {

    // 0. Count menu by name
    int countByName(String name);
    // 1. Find menus by name
    ArrayList<Menu> findByName(String name);
    // 3. Find menus by name
    long count();


    // 1. Find menus by email
    //Optional<Menu> findByEmail(String email);

    // 2. Find menus by phone number
    //Menu findByPhoneNumber(String phoneNumber);

    // 3. Find menus by name
    //Menu findByNameAndEmail(String name, String email);

    // 4. Find menus by name
    Menu findByNameContaining(String letter);

    // 5. Find menus by age greater than a specified value
    //List<Menu> findByAgeGreaterThan(int age);

    // 6. Find menus by age less than a specified value and not deleted
    //List<Menu> findByAgeLessThanAndDeletedFalse(int age);

    // 7. Find menus by name containing a specific string (case-insensitive)
    List<Menu> findByNameContainingIgnoreCase(String namePart);

    // 8. Count menus by age
    //long countByAge(int age);

    // 9. Find menus by email ending with a specific domain
    //List<Menu> findByEmailEndingWith(String domain);

    // 10. Find top 5 oldest menus
    //List<Menu> findTop5ByOrderByAgeDesc();

    // 11. Find menus by name and age
    //List<Menu> findByNameAndAge(String name, int age);

    // 12. Find non-deleted menus ordered by name
    //List<Menu> findByVipCustomerFalseOrderByNameAsc();
    

}