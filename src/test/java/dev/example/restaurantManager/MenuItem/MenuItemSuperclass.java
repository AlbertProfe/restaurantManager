package dev.example.restaurantManager.MenuItem;

import dev.example.restaurantManager.repository.DessertRepository;
import dev.example.restaurantManager.repository.MainCourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuItemSuperclass {

    @Autowired
    DessertRepository dessertRepository;
    @Autowired
    MainCourseRepository mainCourseRepository;

    @Test
    void testMethodsFromSuperclass(){


    }

}
