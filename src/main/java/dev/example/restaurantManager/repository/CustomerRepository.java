package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerRestaurant, String> {

    // 0. Count customers by name
    int countByName(String name);
    // 1. Find customers by name
    ArrayList<CustomerRestaurant> findByName(String name);
    // 3. Find customers by name
    long count();


    // 1. Find customers by email
    Optional<CustomerRestaurant> findByEmail(String email);

    // 2. Find customers by phone number
    CustomerRestaurant findByPhoneNumber(String phoneNumber);

    // 3. Find customers by name
    CustomerRestaurant findByNameAndEmail(String name, String email);

    // 4. Find customers by name
    CustomerRestaurant findByNameContaining(String letter);

    // 5. Find customers by age greater than a specified value
    List<CustomerRestaurant> findByAgeGreaterThan(int age);

    // 6. Find customers by age less than a specified value and not deleted
    List<CustomerRestaurant> findByAgeLessThanAndDeletedFalse(int age);

    // 7. Find customers by name containing a specific string (case-insensitive)
    List<CustomerRestaurant> findByNameContainingIgnoreCase(String namePart);

    // 8. Count customers by age
    long countByAge(int age);

    // 9. Find customers by email ending with a specific domain
    List<CustomerRestaurant> findByEmailEndingWith(String domain);

    // 10. Find top 5 oldest customers
    List<CustomerRestaurant> findTop5ByOrderByAgeDesc();

    // 11. Find customers by name and age
    List<CustomerRestaurant> findByNameAndAge(String name, int age);

    // 12. Find non-deleted customers ordered by name
    List<CustomerRestaurant> findByVipCustomerFalseOrderByNameAsc();
    

}