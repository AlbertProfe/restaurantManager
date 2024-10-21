package dev.example.restaurantManager;

<<<<<<< HEAD

import dev.example.restaurantManager.utilities.DataLoader;
import org.springframework.boot.ApplicationRunner;
=======
>>>>>>> master
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagerApplication.class, args);
	}

<<<<<<< HEAD

	@Bean
	public ApplicationRunner customerDataLoader(DataLoader DataLoader) {
		return args -> DataLoader.createFakeAllData();

	}

=======
>>>>>>> master
}