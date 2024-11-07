package dev.example.restaurantManager;

import dev.example.restaurantManager.model.Dessert;
import dev.example.restaurantManager.model.MainCourse;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.repository.DessertRepository;
import dev.example.restaurantManager.repository.MainCourseRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
class MenuItemTest {

    @Autowired
    private DessertRepository dessertRepository;
    @Autowired
    private MainCourseRepository mainCourseRepository;

    @Test
    public void DessertRepositoryTest() {
        Dessert dessert = new Dessert(
                "D03",
                "Apple Pie",
                "Warm apple pie with cinnamon",
                6.50,
                true
        );
        dessertRepository.save(dessert);

        Dessert found = dessertRepository.findById("D03").orElse(null);
        assertNotNull(found);
        assertEquals("Apple Pie", found.getName());
        assertTrue(found.getToShare());
    }

    @Test
    public void MainCourseRepositoryTest() {

        MainCourse mainCourse = new MainCourse(
                "MC03",
                "Salmon",
                "Grilled salmon with spices",
                15.99,
                "Large"
        );
        mainCourseRepository.save(mainCourse);

        MainCourse found = mainCourseRepository.findById("MC03").orElse(null);
        assertNotNull(found);
        assertEquals("Salmon", found.getName());
        assertEquals("Large", found.getServingSize());
    }

    @Test
    void testPolymorphicBehavior() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MainCourse("MC02", "Pasta", "Pasta with tomato sauce", 10.50, "Medium"));
        menuItems.add(new Dessert("D02", "Ice Cream", "Vanilla flavor", 5.99, false));

        for (MenuItem item : menuItems) {
            assertNotNull(item.getName());
            assertTrue(item.getPrice() > 0);
            assertTrue(item.getDescription().length() > 0);
        }
    }

}


