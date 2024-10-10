package dev.example.restaurantManager;

import dev.example.restaurantManager.utilities.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantManagerApplication implements CommandLineRunner {

	@Autowired
	private DataLoader dataLoader;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataLoader.loadAllData();
	}
}
