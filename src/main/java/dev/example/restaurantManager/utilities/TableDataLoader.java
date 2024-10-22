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
                TableRestaurant table_rest = new TableRestaurant();
                        table_rest.setId(UUID.randomUUID().toString());
                        table_rest.setName("Table " + (i + 1));
                        table_rest.setDescription(faker.lorem().sentence());
                        table_rest.setQty(faker.number().numberBetween(1, 10));
                        table_rest.setBusy(faker.bool().bool());  //


                tableRepository.save(table_rest);
            }

            System.out.println(qty + " fake customers have been created and saved to the database.");
        }
    }
}
