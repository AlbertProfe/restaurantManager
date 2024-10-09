package dev.example.restaurantManager.utilities;

import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class TableDataLoader {

    @Autowired
    private TableRepository tableRepository;

    public void createFakeTables() {
        // Check if the database is empty
        if (tableRepository.count() != 0) {
            return;
        }

        System.out.println(" 0 tables at the database found");
//        Faker faker = new Faker(new Locale("en-US"));

        Random random = new Random();
        int tablesQty = 12;
        // Create and save 100 fake customers
        for (int i = 1; i <= tablesQty; i++) {
            String tableName = "Table " + (i < 10 ? "0" : "") + i;
            int tableFor = 2 + random.nextInt(5 ); // people in table from 2 to 6
            Table table = new Table(
                    UUID.randomUUID().toString(),
                    tableName,
                    tableName + " description",
                    tableFor,
                    false
                    );
            tableRepository.save(table);
        }

        System.out.println(tablesQty + " fake tables have been created and saved to the database.");
    }

}
