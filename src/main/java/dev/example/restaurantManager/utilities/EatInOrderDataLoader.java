package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.repository.EatInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class EatInOrderDataLoader {

    @Autowired
    private EatInOrderRepository eatInOrderRepository;

    public void createFakeEatInOrders() {
        // Check if the database is empty
        if (eatInOrderRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));

            int qty = 10;
            // Create and save 5 fake eatinorders

            //String id, Date date, String waiter, int peopleQty, double totalPayment, boolean paid
            for (int i = 0; i < qty; i++) {
                EatInOrderRestaurant eatInOrderRestaurant = new EatInOrderRestaurant(
                        UUID.randomUUID().toString(),
                        faker.date(),
                        faker.friends().character(),
                        faker.random().nextInt(1, 10),
                        faker.random().nextDouble(),
                        faker.random().nextBoolean()

                );
                eatInOrderRepository.save(eatInOrderRestaurant);
            }

            System.out.println(qty + " fake EatInOrders have been created and saved to the database.");
        }
    }
}
