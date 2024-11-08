package dev.example.restaurantManager.menuItem;

import dev.example.restaurantManager.model.MenuItem.Dessert;
import dev.example.restaurantManager.model.MenuItem.MainCourse;
import dev.example.restaurantManager.model.MenuItem.MenuItem;
import dev.example.restaurantManager.repository.DessertRepository;
import dev.example.restaurantManager.repository.MainCourseRepository;
import dev.example.restaurantManager.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class DessertTest {

    @Autowired
    private DessertRepository dessertRepository;
    @Autowired
    private MainCourseRepository mainCourseRepository;

    @Test
    void newInterfaceDessert_InMemory(){
        String id = "MI001";
        String name = "item 1";
        String description = "item 1 description";
        double price = 20.0;
        boolean sugarFree = true;
        Dessert dessert = new Dessert();
        dessert.setId(id);
        dessert.setName(name);
        dessert.setDescription(description);
        dessert.setPrice(price);
        dessert.setSugarFree(sugarFree);

        assertThat(dessert.getId()).isEqualTo(id);
        assertThat(dessert.getName()).isEqualTo(name);
        assertThat(dessert.getDescription()).isEqualTo(description);
        assertThat(dessert.getPrice()).isEqualTo(price);
        assertThat(dessert.isSugarFree()).isEqualTo(sugarFree);
    }

    @Test
    void newInterfaceDessert_DB(){
        Dessert dessert = dessertRepository.findAll().getFirst();

        assertThat(dessert.getId()).isNotNull();
        assertThat(dessert.getName()).isNotNull();
        assertThat(dessert.getDescription()).isNotNull();
        assertThat(dessert.getPrice()).isNotNull();
        assertThat(dessert.isSugarFree()).isNotNull();
    }

    @Test
    void newInterfaceMainCourse_InMemory(){
        String id = "MI001";
        String name = "item 1";
        String description = "item 1 description";
        double price = 20.0;
        boolean vegan = false;
        MainCourse mainCourse = new MainCourse();
        mainCourse.setId(id);
        mainCourse.setName(name);
        mainCourse.setDescription(description);
        mainCourse.setPrice(price);
        mainCourse.setVegan(vegan);

        assertThat(mainCourse.getId()).isEqualTo(id);
        assertThat(mainCourse.getName()).isEqualTo(name);
        assertThat(mainCourse.getDescription()).isEqualTo(description);
        assertThat(mainCourse.getPrice()).isEqualTo(price);
        assertThat(mainCourse.isVegan()).isEqualTo(vegan);
    }

    @Test
    void newInterfaceMainCourse_DB(){
        MainCourse mainCourse = mainCourseRepository.findAll().getFirst();

        assertThat(mainCourse.getId()).isNotNull();
        assertThat(mainCourse.getName()).isNotNull();
        assertThat(mainCourse.getDescription()).isNotNull();
        assertThat(mainCourse.getPrice()).isNotNull();
        assertThat(mainCourse.isVegan()).isNotNull();
    }

}
