package dev.example.restaurantManager;

import dev.example.restaurantManager.model.EatInOrderRestaurant;
import dev.example.restaurantManager.model.MenuRestaurant;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EatInOrderTest {
    @Autowired
    private MenuRestaurantRepository menuRestaurantRepository;
    @Autowired
    private EatInOrderRepository eatInOrderRepository;
    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    @Test
    public void TestCreateEatInOrder() {
        // Crear y asignar valores a las mesas
        TableRestaurant table1 = new TableRestaurant();
        table1.setId(UUID.randomUUID().toString());
        table1.setName("T1");
        table1.setDescription("Corner Table");
        table1.setQty(2);
        table1.setBusy(false);

        TableRestaurant table2 = new TableRestaurant();
        table2.setId(UUID.randomUUID().toString());
        table2.setName("T2");
        table2.setDescription("Corner Table");
        table2.setQty(2);
        table2.setBusy(false);

        TableRestaurant table3 = new TableRestaurant();
        table3.setId(UUID.randomUUID().toString());
        table3.setName("T3");
        table3.setDescription("Corner Table");
        table3.setQty(2);
        table3.setBusy(false);

        // Crear 3 menús
        MenuRestaurant menuRestaurant1 = new MenuRestaurant("M01", "Burger Menu", 10.99, "Burger, fries, and drink", true, true, null);
        MenuRestaurant menuRestaurant2 = new MenuRestaurant("M02", "Pizza Menu", 12.99, "Pizza and salad", true, false, null);
        MenuRestaurant menuRestaurant3 = new MenuRestaurant("M03", "Salad Menu", 8.99, "Mixed salad and dressing", true, true, null);

        // Guardar las mesas
        tableRestaurantRepository.saveAll(Arrays.asList(table1, table2, table3));

        // Guardar los menús
        menuRestaurantRepository.saveAll(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3));

        // Crear los pedidos y asignar menús y mesas
        EatInOrderRestaurant eatInOrderRestaurant1 = new EatInOrderRestaurant("E01", new Date(), "John", 4, 43.96, true, table1, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant2, menuRestaurant3)));
        EatInOrderRestaurant eatInOrderRestaurant2 = new EatInOrderRestaurant("E02", new Date(), "John", 4, 43.96, true, table2, new ArrayList<>(Arrays.asList(menuRestaurant1, menuRestaurant3)));
        EatInOrderRestaurant eatInOrderRestaurant3 = new EatInOrderRestaurant("E03", new Date(), "John", 4, 43.96, true, table3, new ArrayList<>(Arrays.asList(menuRestaurant2)));

        // Sincronizar la relación bidireccional
        table1.getEatInOrderRestaurants().add(eatInOrderRestaurant1);
        eatInOrderRestaurant1.setTableRestaurant(table1);

        table2.getEatInOrderRestaurants().add(eatInOrderRestaurant2);
        eatInOrderRestaurant2.setTableRestaurant(table2);

        table3.getEatInOrderRestaurants().add(eatInOrderRestaurant3);
        eatInOrderRestaurant3.setTableRestaurant(table3);

        // Guardar los pedidos
        eatInOrderRepository.saveAll(Arrays.asList(eatInOrderRestaurant1, eatInOrderRestaurant2, eatInOrderRestaurant3));

        // Verificar que se ha guardado la primera orden
        EatInOrderRestaurant foundOrder = eatInOrderRepository.findById("E01")
                .orElseThrow(() -> new RuntimeException("Order not found"));

        System.out.println("Order1: ");
        System.out.println(foundOrder);
    }
}
