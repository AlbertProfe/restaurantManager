package dev.example.restaurantManager;

import dev.example.restaurantManager.model.CourseType;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuItemRepository;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import jakarta.validation.constraints.AssertTrue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MenuItemTest {

    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MenuRestaurantRepository menuRepository;

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


    @Test
    public void relateMenuItemsToMenuTest() {

        MenuItem i1 = new MenuItem();
        i1.setName("Macarrones carbonara");
        i1.setDescription("Macarrones carbonara desc.");
        i1.setCourseType(CourseType.MAIN);
        i1.setSpicy(false);
        i1.setHasGluten(true);
        i1.setAvailable(true);

        MenuItem i2 = new MenuItem();
        i2.setName("Ensalada de burrata");
        i2.setDescription("Ensalada de burrata desc.");
        i2.setCourseType(CourseType.STARTER);
        i2.setSpicy(false);
        i2.setHasGluten(true);
        i2.setAvailable(true);

        MenuItem i3 = new MenuItem();
        i3.setName("Tiramisú");
        i3.setDescription("Tiramisú desc.");
        i3.setCourseType(CourseType.DESSERT);
        i3.setSpicy(false);
        i3.setHasGluten(true);
        i3.setAvailable(true);

        menuItemRepository.save(i1);
        menuItemRepository.save(i2);
        menuItemRepository.save(i3);

        List<MenuItem> items = Arrays.asList(i1,i2,i3);

        MenuRestaurant m1 = new MenuRestaurant();
        m1.setId("M1");
        m1.setName("Italliano");
        m1.setContent("Menú completo: ensalada, macarrones y tiramisú");
        m1.setPrice(25.50);
        m1.setActive(true);
        m1.setWater(true);
        m1.setItems(items);

        menuRepository.save(m1);

        Optional<MenuRestaurant> m1Found =  menuRepository.findById("M1");

        assertThat(m1Found).isPresent();
        MenuRestaurant m1DB = m1Found.get();
        assertThat(m1DB.getId().equals(m1.getId())).isEqualTo(true);
        assertThat(m1DB.getItems().size() == m1.getItems().size()).isEqualTo(true);
        assertThat(m1DB.getItems().get(0).equals(m1.getItems().get(0))).isEqualTo(true);


    }


}