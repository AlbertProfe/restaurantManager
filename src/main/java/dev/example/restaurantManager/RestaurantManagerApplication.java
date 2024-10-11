package dev.example.restaurantManager;


import dev.example.restaurantManager.utilities.DataLoader;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RestaurantManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagerApplication.class, args);
	}


	@Bean
	public ApplicationRunner customerDataLoader(DataLoader DataLoader) {
		return args -> DataLoader.createFakeAllData();

	}

}