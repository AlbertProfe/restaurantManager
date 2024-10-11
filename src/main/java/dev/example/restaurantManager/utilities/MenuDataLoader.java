package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Menu;
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

    public void createFakeMenus(){
        if(menuRepository.count()==0){
            System.out.println("o records at the database found");
            Faker faker = new Faker(new Locale("en-US"));
            Random random = new Random();
            String[]menuNames = {"Day Menu", "Night Menu", "Weekend Menu",
                    "Vegetarian Menu", "Kids Menu", "Gourmet Menu"
            };
            int qty =  50;

            for(int i = 0; i< qty ;i++){
                Menu menu = new Menu();
                menu.setId(UUID.randomUUID().toString());
                String menuName = menuNames[random.nextInt(menuNames.length)];
                menu.setName(menuName);
                menu.setPrice(faker.number().randomDouble(2,5,100));
                menu.setContent(faker.food().dish());
                menu.setActive(faker.bool().bool());
                menu.setWater(faker.bool().bool());

                menuRepository.save(menu);
            }

            System.out.println(qty + " fake menus have been created and saved to the database");

        }
    }
}
