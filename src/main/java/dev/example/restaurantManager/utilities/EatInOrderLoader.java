package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.EatInOrder;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EatInOrderDataLoader extends DataLoader {

    @Autowired
    private EatInOrderRepository eatInOrderRepository;

    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    public void createFakeEatInOrders() {
        if (eatInOrderRepository.count() == 0) {
            System.out.println("0 eat-in orders found in the database");

            Faker faker = new Faker(new Locale("en-US"));
            int qty = 20;

            List<TableRestaurant> tables = tableRestaurantRepository.findAll();

            for (int i = 1; i <= qty; i++) {
                EatInOrder eatInOrder = new EatInOrder(
                        UUID.randomUUID().toString(),
                        faker.date(),
                        faker.name().fullName(),
                        faker.number().numberBetween(1, 8),
                        faker.number().randomDouble(2, 10, 100),
                        faker.random().nextBoolean(),
                        new ArrayList<>()
                        //tables
                );

                if (!tables.isEmpty()) {
                    eatInOrder.setTableRestaurant(tables.get(faker.number().numberBetween(0, tables.size())));
                }

                eatInOrderRepository.save(eatInOrder);
            }

            System.out.println(qty + " fake eat-in orders have been created and saved to the database.");
        }
    }
}
