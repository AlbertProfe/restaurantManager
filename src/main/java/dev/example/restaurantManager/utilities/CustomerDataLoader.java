package dev.example.restaurantManager.utilities;

import com.github.javafaker.Faker;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Locale;
import java.util.UUID;

@Component
public class CustomerDataLoader {

    @Autowired
    private CustomerRepository customerRepository;

    public void createFakeCustomers() {
        // Check if the database is empty
        if (customerRepository.count() == 0) {
            System.out.println(" 0 records at the database found");
            Faker faker = new Faker(new Locale("en-US"));

            int qty = 50;
            // Create and save 100 fake customers
            for (int i = 0; i < qty; i++) {
                Customer customer = new Customer(
                        UUID.randomUUID().toString(),
                        faker.name().fullName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().cellPhone(),
                        faker.random().nextInt(18, 130),
                        faker.random().nextBoolean(),
                        faker.random().nextBoolean()

                );
                customerRepository.save(customer);
            }

            System.out.println(qty + " fake customers have been created and saved to the database.");
        }
    }
}