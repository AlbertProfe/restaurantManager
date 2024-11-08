package dev.example.restaurantManager.menuItem;

import dev.example.restaurantManager.model.MenuItem.MainCourse;
import dev.example.restaurantManager.repository.MainCourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MainCourseTest {

    @Autowired
    private MainCourseRepository mainCourseRepository;

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



