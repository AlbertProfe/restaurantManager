package dev.example.restaurantManager.MenuItem;

import dev.example.restaurantManager.model.Dessert;
import dev.example.restaurantManager.model.MainCourse;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.repository.DessertRepository;
import dev.example.restaurantManager.repository.MainCourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.awt.*;

@SpringBootTest
public class MenuItemSuperclass {

    @Autowired
    DessertRepository dessertRepository;
    @Autowired
    MainCourseRepository mainCourseRepository;

    @Test
    void testMethodsFromSuperclass(){
        // Create objects
        Dessert dessert1 = new Dessert("Ice-cream", "2 balls with cream", 4.5, false, false);
        MainCourse mainCourse1 = new MainCourse("Steak", "Meat with french fries", 18.50, false, true);
        // Saving objects in their repositories
        dessertRepository.save(dessert1);
        mainCourseRepository.save(mainCourse1);

       String name = dessert1.getName();
       double price = mainCourse1.getPrice();

        // Checking if name and price are ok using methods from MenuItem's interface
        assertThat(dessert1.getName()).isEqualTo("Ice-cream");
        assertThat(mainCourse1.getPrice()).isEqualTo(18.50);

        // Printing result
        System.out.println("The name from dessert is: " + name);
        System.out.println("The price from the main course is: " + price);

    }

}
