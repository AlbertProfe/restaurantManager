package dev.example.restaurantManager;


import dev.example.restaurantManager.utilities.CustomerDataLoader;
//import dev.example.restaurantManager.utilities.EatInOrderDataLoader;
import dev.example.restaurantManager.utilities.MenuDataLoader;
import dev.example.restaurantManager.utilities.TableDataLoader;
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
	public ApplicationRunner CustomerDataLoader(CustomerDataLoader customerDataLoader) {
		return args -> customerDataLoader.createFakeCustomers();
	}

	@Bean
	public ApplicationRunner TableDataLoader(TableDataLoader tableDataLoader) {
		return args -> tableDataLoader.createFakeTables();
	}

	@Bean
	public ApplicationRunner MenuDataLoader(MenuDataLoader menuDataLoader) {
		return args -> menuDataLoader.createFakeMenus();
	}

	/*@Bean
	public ApplicationRunner EatInOrderDataLoader(EatInOrderDataLoader eatInOrderDataLoader) {
		return args -> eatInOrderDataLoader.createFakeEatInOrders();
	}*/
}