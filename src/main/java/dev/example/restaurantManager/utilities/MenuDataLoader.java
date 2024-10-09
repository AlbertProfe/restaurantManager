package dev.example.restaurantManager.utilities;

import dev.example.restaurantManager.model.Menu;
import dev.example.restaurantManager.model.Table;
import dev.example.restaurantManager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        Random random = new Random();
        int menusQty = 6;
        // Create and save 100 fake customers
        for (int i = 1; i <= menusQty; i++) {
            String menuName = "Menu " + (i < 10 ? "0" : "") + i;
            // random price between 7.95 and 30.00
            double price = (double)random.nextInt(80, 301) / 10.0;
            price -= random.nextInt(6) < 4 ? 0 :0.05;
            boolean active = true;
            // water is included in menu
            boolean water = random.nextInt(4 ) > 0; // water included
            Menu menu = new Menu(
                    UUID.randomUUID().toString(),
                    menuName,
                    price,
                    menuName + " content",
                    active,
                    water
            );
            menuRepository.save(menu);
        }

        System.out.println(menusQty + " fake menus have been created and saved to the database.");
    }

}
