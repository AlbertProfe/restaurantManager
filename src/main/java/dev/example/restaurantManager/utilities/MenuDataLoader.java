package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.repository.MenuRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class MenuDataLoader {

    @Autowired
    private MenuRestaurantRepository menuRepository;

    public void createFakeMenus() {
        // Check if the database is empty
        if (menuRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));

            int qty = 5;
            // Create and save 5 fake menus
            for (int i = 0; i < qty; i++) {
                MenuRestaurant menu = new MenuRestaurant(
                        UUID.randomUUID().toString(),
                        faker.friends().character(),
                        faker.random().nextDouble(),
                        faker.food().dish(),
                        faker.random().nextBoolean(),
                        faker.random().nextBoolean()

                );
                menuRepository.save(menu);
            }

            System.out.println(qty + " fake menus have been created and saved to the database.");
        }
    }
}
