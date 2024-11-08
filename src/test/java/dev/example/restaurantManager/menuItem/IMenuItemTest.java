package dev.example.restaurantManager.menuItem;

import dev.example.restaurantManager.model.MenuItem.MenuItem;
import dev.example.restaurantManager.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class IMenuItemTest {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    void newInterfaceMenuItem_InMemory(){
        String id = "MI001";
        String name = "item 1";
        String description = "item 1 description";
        double price = 20.0;
        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        menuItem.setName(name);
        menuItem.setDescription(description);
        menuItem.setPrice(price);

        assertThat(menuItem.getId()).isEqualTo(id);
        assertThat(menuItem.getName()).isEqualTo(name);
        assertThat(menuItem.getDescription()).isEqualTo(description);
        assertThat(menuItem.getPrice()).isEqualTo(price);
    }

    @Test
    void newInterfaceMenuItem_DB(){
        MenuItem menuItem = menuItemRepository.findAll().getFirst();

        assertThat(menuItem.getId()).isNotNull();
        assertThat(menuItem.getName()).isNotNull();
        assertThat(menuItem.getDescription()).isNotNull();
        assertThat(menuItem.getPrice()).isNotNull();
    }

}
