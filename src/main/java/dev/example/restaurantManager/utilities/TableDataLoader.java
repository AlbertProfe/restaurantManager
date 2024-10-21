package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class TableDataLoader {

    @Autowired
    private TableRestaurantRepository tableRepository;

    public void createFakeTables() {
        // Check if the database is empty
        if (tableRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));

            int qty = 10;
            // Create and save 100 fake customers
            for (int i = 0; i < qty; i++) {
                TableRestaurant table_rest = new TableRestaurant(
                        UUID.randomUUID().toString(),
                        faker.numerify("Table"+i),
                        faker.rickAndMorty().character(),
                        faker.random().nextInt(1, 10),
                        faker.random().nextBoolean()

                );
                tableRepository.save(table_rest);
            }

            System.out.println(qty + " fake customers have been created and saved to the database.");
        }
    }
}
