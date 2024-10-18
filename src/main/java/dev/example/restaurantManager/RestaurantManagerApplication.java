package dev.example.restaurantManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@PropertySource("classpath:application.properties")
@SpringBootApplication
public class RestaurantManagerApplication {

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