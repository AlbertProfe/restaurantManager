package dev.example.restaurantManager.menuItem;

import dev.example.restaurantManager.model.MenuItem.Dessert;
import dev.example.restaurantManager.repository.DessertRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DessertRepositoryTest {

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
    public void insertDessert(){
        Dessert insertedDessert = dessertRepository.save(dessert);
        assertThat(insertedDessert).isNotNull();
        assertThat(insertedDessert.getDescription()).isEqualTo(dessert.getDescription());
    }

    @Test
    public void deleteDessert(){
        Dessert insertedDessert = dessertRepository.save(dessert);
        assertThat(insertedDessert).isNotNull();
        dessertRepository.deleteById(insertedDessert.getId());
        Optional<Dessert> deletedDessert = dessertRepository.findById(insertedDessert.getId());
        assertThat(deletedDessert).isNotPresent();
    }

    @Test
    public void updateDessert(){
        Dessert insertedDessert = dessertRepository.save(dessert);
        assertThat(insertedDessert).isNotNull();
        String newDescription = "This is a new description for our fake dessert";
        assertThat(insertedDessert.getDescription()).isNotEqualTo(newDescription);
        insertedDessert.setDescription(newDescription);
        Dessert updatedDessert = dessertRepository.save(insertedDessert);
        assertThat(updatedDessert).isNotNull();
        assertThat(updatedDessert.getId()).isEqualTo(insertedDessert.getId());
        assertThat(updatedDessert.getDescription()).isEqualTo(newDescription);
    }

    @Test
    public void findAll(){
        long nDessertsBeforeInsert = dessertRepository.findAll().size();
        Dessert insertedDessert = dessertRepository.save(dessert);
        long nDessertsAfterInsert = dessertRepository.findAll().size();
        assertThat(nDessertsAfterInsert).isEqualTo(nDessertsBeforeInsert+1);
    }

    @Test
    public void findById(){
        Dessert insertedDessert = dessertRepository.save(dessert);
        assertThat(insertedDessert).isNotNull();
        Optional<Dessert> foundDessert = dessertRepository.findById(insertedDessert.getId());
        assertThat(foundDessert).isPresent();
    }

    @Test
    public void findByIdNonexistentDessert(){
        Optional<Dessert> foundDessert = dessertRepository.findById("Non existent dessert ID");
        assertThat(foundDessert).isNotPresent();
    }

}
