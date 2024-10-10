package dev.example.restaurantManager.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    private CustomerDataLoader customerDataLoader;

    @Autowired
    private MenuDataLoader menuDataLoader;

    @Autowired
    private TableDataLoader tableDataLoader;

    public void loadAllData() {
        System.out.println("Starting data loading...");

        customerDataLoader.createFakeCustomers();
        menuDataLoader.createFakeMenus();
        tableDataLoader.createFakeTables();

        System.out.println("Data loading completed.");
    }
}
