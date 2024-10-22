package dev.example.restaurantManager.repository;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface EatInOrderRepository extends JpaRepository<EatInOrderRestaurant, String> {

    // 0. Count tables by name
    int countByDate(Date date);
    // 1. Find tables by name
    ArrayList<EatInOrderRestaurant> findByDate(Date date);
    // 3. Find tables by name
    long count();


    // 1. Find tables by id
    //EatInOrderRestaurant findById(String id);

    // 2. Find tables by capacity
    //Table findByPhoneNumber(String phoneNumber);

    // 3. Find orders by waiter
    EatInOrderRestaurant findByWaiter(String waiter);

    // 4. Find Orders by date
    //EatInOrderRestaurant findByDate(Date date);

    // 5. Find tables by age greater than a specified value
    //List<Customer> findByAgeGreaterThan(int age);

    // 6. Find tables by age less than a specified value and not deleted
    //List<Customer> findByAgeLessThanAndDeletedFalse(int age);

    // 7. Find tables by name containing a specific string (case-insensitive)
    //List<EatInOrderRestaurant> findByNameContainingIgnoreCase(String namePart);

    // 8. Count tables by age
    //long countByAge(int age);

    // 9. Find tables by email ending with a specific domain
    //List<Customer> findByEmailEndingWith(String domain);

    // 10. Find top 5 oldest customers
    //List<Customer> findTop5ByOrderByAgeDesc();

    // 11. Find customers by name and age
    //List<Customer> findByNameAndAge(String name, int age);

    // 12. Find non-deleted tables ordered by name
    //List<Table> findByVipCustomerFalseOrderByNameAsc();


}