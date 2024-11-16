package dev.example.restaurantManager;


import dev.example.restaurantManager.RestaurantManagerApplication;
import dev.example.restaurantManager.model.MainCourse;
import dev.example.restaurantManager.model.MenuItem;
import dev.example.restaurantManager.model.SideCourse;
import dev.example.restaurantManager.repository.MenuItemRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = RestaurantManagerApplication.class)public class MenuItemTest {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp(){
        //Clean database before each test
        menuItemRepository.deleteAll();
    }

    @Test
    public void testMainCourseCreationAndPersistence(){
        //Create a MainCourse Object with specific data

        MainCourse mainCourse = new MainCourse(
                UUID.randomUUID().toString(),
                "Chicken parmesan",
                "Fired chicken with Parmesan cheese",
                12.50,
                "salad",
                450,
                true
        );

        //save to repository
        menuItemRepository.save(mainCourse);

        //retrieve the object since database using the ID

        Optional<MenuItem> retrievedMainCourse = menuItemRepository.findById(mainCourse.getId());

        //verify that the object was saved and retrieved correctly
        assertThat(retrievedMainCourse).isPresent();
        assertThat(retrievedMainCourse.get().getName()).isEqualTo(mainCourse.getName());
        assertThat(retrievedMainCourse.get().getDescription()).isEqualTo(mainCourse.getDescription());
        assertThat(retrievedMainCourse.get().getPrice()).isEqualTo(mainCourse.getPrice());

        // Print a message confirming it was saved correctly
        System.out.println("MainCourse saved successfully: " + retrievedMainCourse.get().getName());

    }

    @Test
    public void testSideCourseCreationAndPersistence() {
        // Create object dessert
        SideCourse dessert = new SideCourse(
                UUID.randomUUID().toString(),
                "Chocolate Cake",
                "Rich chocolate dessert",
                6.00,
                false,
                "Chocolate",
                8
        );

        // saved database
        menuItemRepository.save(dessert);

        // retrieved object of database find by id
        Optional<MenuItem> retrievedDessert = menuItemRepository.findById(dessert.getId());

        // verify is saved and confirming
        assertThat(retrievedDessert).isPresent();
        assertThat(retrievedDessert.get().getName()).isEqualTo(dessert.getName());
        assertThat(retrievedDessert.get().getDescription()).isEqualTo(dessert.getDescription());
        assertThat(retrievedDessert.get().getPrice()).isEqualTo(dessert.getPrice());

        // Print a message confirming it was saved correctly
        System.out.println("Dessert saved successfully: " + retrievedDessert.get().getName());
    }

    @Test
    @Transactional
    public void testSaveAndRetrieveAllMenuItems() {
        //create objects MainCourse and Dessert

        MainCourse mainCourse = new MainCourse(
                UUID.randomUUID().toString(),
                "Steak",
                "Grilled steak with potatoes",
                20.00,
                "fries",
                600,
                false
        );

        SideCourse sidecourse = new SideCourse(UUID.randomUUID().toString(),
                "Salad",
                "Mediterranean Style Salad",
                5.50,
                true,
                "salad",
                6);

        //saved both objets in database

        menuItemRepository.save(mainCourse);
        menuItemRepository.save(sidecourse);

        //verify that both objects can retrieve
        Iterable<MenuItem> allMenuItems = menuItemRepository.findAll();
        System.out.println("All Menu Items:");
        for (MenuItem menuItem : allMenuItems) {
            System.out.println(menuItem.getName() + ": " + menuItem.getDescription());
        }
        assertThat(allMenuItems).hasSize(2);
        assertThat(menuItemRepository.count()).isEqualTo(2);

    }


    @Test
    public void testUpdateMainCourse() {
        // Create and save MainCourse
        MainCourse savedMainCourse = menuItemRepository.save(new MainCourse(
                UUID.randomUUID().toString(),
                "Chicken Breast",
                "Grilled chicken breast",
                14.00,
                "vegetable",
                500,
                true
        ));

        savedMainCourse.setName("Chicken Crispy");

        //save update maincourse

        MainCourse updateMainCourse = menuItemRepository.save(savedMainCourse);

        //verify updated

        assertThat(updateMainCourse.getName()).isEqualTo("Chicken Crispy");


    }

    @Test
    public void testDeleteMainCourse() {
        // Create and save MainCourse
        MenuItem savedMainCourse = menuItemRepository.save(new MainCourse(
                UUID.randomUUID().toString(),
                "Beef Wellington",
                "Tender beef wrapped in pastry",
                25.00,
                "vegetables",
                700,
                false
        ));

        // Delete the item
        menuItemRepository.delete(savedMainCourse);

        // Verifi that the item doesn't exists in the database
        Optional<MenuItem> deletedItem = menuItemRepository.findById(savedMainCourse.getId());
        assertThat(deletedItem).isNotPresent();
    }

    @Test
    public void testGetItemType() {
        // Create a MainCourse and Dessert
        MenuItem mainCourse = new MainCourse(
                UUID.randomUUID().toString(),  // Generar un ID único
                "Grilled Chicken",
                "Grilled chicken with vegetables",
                12.50,
                "vegetable",
                500,
                true
        );

        MenuItem sidecourse = new SideCourse(
                UUID.randomUUID().toString(),
                "Green Beans",
                "Fresh steamed green beans",
                6.00,
                false,
                "beans",
                8
        );

        // check that the type is correct before saving.
        assertThat(mainCourse.getItemType()).isEqualTo("MainCourse");
        assertThat(sidecourse.getItemType()).isEqualTo("Dessert");

        // save in database
        menuItemRepository.save(mainCourse);
        menuItemRepository.save(sidecourse);

        // retrieved and verify the type is correct.
        Optional<MenuItem> retrievedMainCourse = menuItemRepository.findById(mainCourse.getId());
        Optional<MenuItem> retrievedDessert = menuItemRepository.findById(sidecourse.getId());

        assertThat(retrievedMainCourse).isPresent();
        assertThat(retrievedMainCourse.get().getItemType()).isEqualTo("MainCourse");

        assertThat(retrievedDessert).isPresent();
        assertThat(retrievedDessert.get().getItemType()).isEqualTo("SideCourse");
    }
}