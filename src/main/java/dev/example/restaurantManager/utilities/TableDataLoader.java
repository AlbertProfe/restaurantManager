package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.repository.CustomerRepository;
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
        if (tableRepository.count() == 0) {
            System.out.println("0 records found in the database");

            Faker faker = new Faker(new Locale("en-US"));
            int qty = 10;

            for (int i = 0; i < qty; i++) {
                Table table = new Table(
                        UUID.randomUUID().toString(),
                        "table-" + faker.number().numberBetween(1, qty),
                        generateMaterialDescription(faker),
                        faker.number().numberBetween(1, 20),
                        faker.random().nextBoolean()
                );

                tableRepository.save(table);
            }

            System.out.println(qty + " fake tables have been created and saved to the database.");
        }
    }

    private String generateMaterialDescription(Faker faker) {
        String adjective = faker.commerce().material();
        String color = faker.color().name();
        return adjective + " " + color + " table";
    }

}