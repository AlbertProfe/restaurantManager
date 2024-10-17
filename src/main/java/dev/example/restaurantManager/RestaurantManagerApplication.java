package dev.example.restaurantManager;

import dev.example.restaurantManager.utilities.CustomerDataLoader;
import dev.example.restaurantManager.utilities.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:application.properties")
@SpringBootApplication
public class RestaurantManagerApplication {

	@Autowired
	private DataLoader dataLoader;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagerApplication.class, args);
	}
/*

	@Bean
	public ApplicationRunner dataLoader(CustomerDataLoader customerDataLoader) {
		return args -> customerDataLoader.createFakeCustomers();
	}
*/

}