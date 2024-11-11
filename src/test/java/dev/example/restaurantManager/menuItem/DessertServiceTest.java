package dev.example.restaurantManager.menuItem;

import dev.example.restaurantManager.model.MenuItem.Dessert;
import dev.example.restaurantManager.repository.DessertRepository;
import dev.example.restaurantManager.service.DessertService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DessertServiceTest {

    @Autowired
    DessertService dessertService;

    @Autowired
    DessertRepository dessertRepository;

    private final String description = "This dessert is only for test";
    private Dessert dessert;

    @BeforeEach
    public void setup(){
        dessert = new Dessert("ID-will-change-on-insert-to-db","Dessert test",description,6.66,true);
    }

    @AfterEach
    public void deleteFakeInsertedDessert(){
        Optional<Dessert> foundDessert = dessertRepository.findByDescription(description);
        foundDessert.ifPresent(value -> dessertRepository.delete(value));
    }

    @Test
    public void createDessert(){
        Dessert newDessert = dessertService.createDessert(dessert);
        assertThat(newDessert).isNotNull();
        assertThat(newDessert.getDescription()).isEqualTo(dessert.getDescription());
    }

    @Test
    public void deleteDessert(){
        Dessert insertedDessert = dessertRepository.save(dessert);
        assertThat(insertedDessert).isNotNull();
        boolean deleted = dessertService.deleteDessert(insertedDessert.getId());
        assertThat(deleted).isEqualTo(true);
    }

    @Test
    public void updateDessert(){
        Dessert insertedDessert = dessertRepository.save(dessert);
        assertThat(insertedDessert).isNotNull();
        String newDescription = "This is a new description for our fake dessert";
        dessert.setDescription(newDescription);
        Dessert updatedDessert = dessertService.updateDessert(insertedDessert.getId(),dessert);
        assertThat(updatedDessert).isNotNull();
        assertThat(updatedDessert.getId()).isEqualTo(insertedDessert.getId());
        assertThat(updatedDessert.getDescription()).isEqualTo(newDescription);

    }

    @Test
    public void findAll(){
        long nDesserts = dessertRepository.count();
        List<Dessert> desserts = dessertService.getAllDesserts();
        assertThat(desserts.size()).isEqualTo(nDesserts);
    }

    @Test
    public void findById(){
        Dessert insertedDessert = dessertRepository.save(dessert);
        assertThat(insertedDessert).isNotNull();
        Dessert foundDessert = dessertService.getDessertById(insertedDessert.getId());
        assertThat(foundDessert).isNotNull();
        assertThat(foundDessert.getDescription()).isEqualTo(insertedDessert.getDescription());
    }

    @Test
    public void findByIdNonexistentDessert(){
        Dessert foundDessert = dessertService.getDessertById("Non existent dessert ID");
        assertThat(foundDessert).isNull();
    }


}
