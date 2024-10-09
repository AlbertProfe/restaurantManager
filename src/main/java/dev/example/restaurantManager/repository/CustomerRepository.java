package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    // 0. Count customers by name
    int countByName(String name);
    // 1. Find customers by name
    ArrayList<Customer> findByName(String name);
    // 3. Find customers by name
    long count();


    // 1. Find customers by email
    Optional<Customer> findByEmail(String email);

    // 2. Find customers by phone number
    Customer findByPhoneNumber(String phoneNumber);

    // 3. Find customers by name
    Customer findByNameAndEmail(String name, String email);

    // 4. Find customers by name
    Customer findByNameContaining(String letter);

    // 5. Find customers by age greater than a specified value
    List<Customer> findByAgeGreaterThan(int age);

    // 6. Find customers by age less than a specified value and not deleted
    List<Customer> findByAgeLessThanAndDeletedFalse(int age);

    // 7. Find customers by name containing a specific string (case-insensitive)
    List<Customer> findByNameContainingIgnoreCase(String namePart);

    // 8. Count customers by age
    long countByAge(int age);

    // 9. Find customers by email ending with a specific domain
    List<Customer> findByEmailEndingWith(String domain);

    // 10. Find top 5 oldest customers
    List<Customer> findTop5ByOrderByAgeDesc();

    // 11. Find customers by name and age
    List<Customer> findByNameAndAge(String name, int age);

    // 12. Find non-deleted customers ordered by name
    List<Customer> findByVipCustomerFalseOrderByNameAsc();
    

}