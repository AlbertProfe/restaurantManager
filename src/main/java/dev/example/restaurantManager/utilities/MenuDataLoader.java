package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Component
public class MenuDataLoader {

    @Autowired
    private MenuRepository menuRepository;

    public void createFakeMenus() {
        // Check if the database is empty
        if (menuRepository.count() != 0) {
            return;
        }

        System.out.println(" 0 menus at the database found");

        Faker faker = new Faker(new Locale("en-US"));


        Random random = new Random();
        int menusQty = 6;
        // Create and save 100 fake customers
        for (int i = 1; i <= menusQty; i++) {
            String menuName = "Menu " + (i < 10 ? "0" : "") + i;
            // random price between 7.95 and 30.00
            double price = faker.number().randomDouble(1, 8, 30)
                    - faker.random().nextInt(6) < 4 ? 0 :0.05;
            Menu menu = new Menu(
                    UUID.randomUUID().toString(),
                    menuName,
                    price,
                    menuName + " content",
                    random.nextInt(4 ) > 0, // 75% of the menus are active
                    random.nextInt(4 ) > 0 // 75% of the menus have water included
            );
            menuRepository.save(menu);
        }

        System.out.println(menusQty + " fake menus have been created and saved to the database.");
    }

}
