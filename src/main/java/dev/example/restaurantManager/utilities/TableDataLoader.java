package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.RestaurantTable;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TableDataLoader {

    @Autowired
    private TableRepository tableRepository;

    public void createFakeTables(){

        // Check if the database is empty
        if (tableRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));

            int qty = 50;
            // Create and save 100 fake customers
            for (int i = 0; i < qty; i++) {
                RestaurantTable table = new RestaurantTable();

                // Using Faker to generate fake data
                table.setName("Table " + (i + 1));
                table.setDescription(faker.lorem().sentence());
                table.setQty(faker.number().numberBetween(1, 10));
                table.setBusy(faker.bool().bool());  //



                tableRepository.save(table);
            }

            System.out.println(qty + " fake tables have been created and saved to the database.");
        }
    }


}
