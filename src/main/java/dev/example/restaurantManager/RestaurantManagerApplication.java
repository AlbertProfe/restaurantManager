package dev.example.restaurantManager;

import dev.example.restaurantManager.utilities.CustomerDataLoader;
import dev.example.restaurantManager.utilities.MenuDataLoader;
import dev.example.restaurantManager.utilities.TableDataLoader;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:application.properties")
@SpringBootApplication
public class RestaurantManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagerApplication.class, args);
	}


	@Bean
	public ApplicationRunner dataLoader(CustomerDataLoader customerDataLoader, TableDataLoader tableDataLoader, MenuDataLoader menuDataLoader) {
		return args -> {
			customerDataLoader.createFakeCustomers();
			tableDataLoader.createFakeTables();
			menuDataLoader.createFakeMenus();
		};
	}


}