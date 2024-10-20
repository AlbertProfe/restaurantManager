package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.CustomerRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class TableDataLoader {

    @Autowired
    private TableRepository tableRepository;

    public void createFakeTables() {
        // Check if the database is empty
        if (tableRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));

           // Create and save 10 fake tables
            for (int i = 0; i < 10; i++) {
                TableRestaurant tableRestaurant = new TableRestaurant(
                        UUID.randomUUID().toString(),
                        faker.name().fullName(),
                        faker.lorem().sentence(),
                        faker.random().nextInt(2, 8),
                        faker.random().nextBoolean()

                );
                tableRepository.save(tableRestaurant);
            }

            System.out.println(10 + " fake tables have been created and saved to the database.");
        }
    }


}
