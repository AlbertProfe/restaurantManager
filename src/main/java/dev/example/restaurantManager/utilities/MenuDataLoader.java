package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class MenuDataLoader {

    @Autowired
    private MenuRepository menuRepository;

    public void createFakeMenus() {
        if (menuRepository.count() == 0) {
            System.out.println("0 menus in the database");

            Faker faker = new Faker(new Locale("en-US"));
            int qty = 10;

            for (int i = 0; i < qty; i++) {
                Menu menu = new Menu(
                        UUID.randomUUID().toString(),
                        faker.food().dish(),
                        faker.number().randomDouble(2, 10, 50),
                        faker.lorem().sentence(),
                        faker.random().nextBoolean(),
                        faker.random().nextBoolean()
                );

                menuRepository.save(menu);
            }

            System.out.println(qty + " fake menus have been created and saved to the database.");
        }
    }

}
