package dev.example.restaurantManager;

import dev.example.restaurantManager.model.CourseType;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.repository.MenuItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class MenuItemTest {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void createMenuItemsInDBTest() {

        MenuItem i1 = new MenuItem();
        i1.setName("Macarrones carbonara");
        i1.setDescription("Macarrones carbonara desc.");
        i1.setCourseType(CourseType.MAIN);
        i1.setSpicy(false);
        i1.setHasGluten(true);
        i1.setAvailable(true);

        menuItemRepository.save(i1);

        MenuItem i2 = new MenuItem();
        i2.setName("Ensalada de burrata");
        i2.setDescription("Ensalada de burrata desc.");
        i2.setCourseType(CourseType.STARTER);
        i2.setSpicy(false);
        i2.setHasGluten(true);
        i2.setAvailable(true);

        menuItemRepository.save(i2);


        MenuItem i3 = new MenuItem();
        i3.setName("Tiramisú");
        i3.setDescription("Tiramisú desc.");
        i3.setCourseType(CourseType.DESSERT);
        i3.setSpicy(false);
        i3.setHasGluten(true);
        i3.setAvailable(true);

        menuItemRepository.save(i3);


        // check if saved
        List<MenuItem> items = menuItemRepository.findAll();
        assert(items.contains(i1));
        assert(items.contains(i2));
        assert(items.contains(i3));

        MenuItem i4 = new MenuItem();
        i4.setName("Panceta");
        i4.setDescription("Panceta desc.");
        i4.setCourseType(CourseType.STARTER);
        i4.setSpicy(false);
        i4.setHasGluten(true);
        i4.setAvailable(true);

        assert(!items.contains(i4));

    }




}